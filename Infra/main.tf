# Définir le fournisseur Azure
provider "azurerm" {
  features {}
  use_msi = true
  subscription_id = "460bc14f-4852-44b0-809b-ce133bada6c4"
}


# Utiliser le groupe de ressources existant
data "azurerm_resource_group" "existing" {
  name = "RFC_Ecom"
}

# Utiliser le registre de conteneurs Azure existant
data "azurerm_container_registry" "existing" {
  name                = "EcomRegistr"
  resource_group_name = data.azurerm_resource_group.existing.name
}

# Créer un cluster Kubernetes (AKS)
resource "azurerm_kubernetes_cluster" "main" {
  name                = "EcomCluster"
  location            = data.azurerm_resource_group.existing.location
  resource_group_name = data.azurerm_resource_group.existing.name
  dns_prefix          = "myEcomcluster"

  default_node_pool {
    name       = "default"
    node_count = 1  # Utilisation de 3 nœuds
    vm_size    = "Standard_DS2_v2"
  }

  identity {
    type = "SystemAssigned"
  }

  network_profile {
    network_plugin = "azure"
    network_policy = "azure"
  }



#   role_based_access_control {
#     enabled = true
#   }

   tags = {
     environment = "EcomRFC"
   }
}

# Définir le réseau virtuel
resource "azurerm_virtual_network" "example" {
  name                = "ecom-vnet"
  location            = data.azurerm_resource_group.existing.location
  resource_group_name = data.azurerm_resource_group.existing.name
  address_space       = ["10.0.0.0/16"]
}

# Définir le sous-réseau
resource "azurerm_subnet" "example" {
  name                 = "ecom-subnet"
  resource_group_name  = data.azurerm_resource_group.existing.name
  virtual_network_name = azurerm_virtual_network.example.name
  address_prefixes     = ["10.0.2.0/24"]
  service_endpoints    = ["Microsoft.Storage"]
  delegation {
    name = "fs"
    service_delegation {
      name = "Microsoft.DBforMySQL/flexibleServers"
      actions = [
        "Microsoft.Network/virtualNetworks/subnets/join/action",
      ]
    }
  }
}

# Définir la zone DNS privée
resource "azurerm_private_dns_zone" "example" {
  name                = "ecom.mysql.database.azure.com"
  resource_group_name = data.azurerm_resource_group.existing.name
}

# Lier la zone DNS privée au réseau virtuel
resource "azurerm_private_dns_zone_virtual_network_link" "example" {
  name                  = "ecomVnetZoneLink"
  private_dns_zone_name = azurerm_private_dns_zone.example.name
  virtual_network_id    = azurerm_virtual_network.example.id
  resource_group_name   = data.azurerm_resource_group.existing.name
}

# Créer le serveur MySQL flexible
resource "azurerm_mysql_flexible_server" "example" {
  name                   = "ecom-fs"
  resource_group_name    = data.azurerm_resource_group.existing.name
  location               = data.azurerm_resource_group.existing.location
  administrator_login    = "mysqladmin"
  administrator_password = var.mysql_admin_password # Utilisez la variable pour le mot de passe admin
  backup_retention_days  = 7
  delegated_subnet_id    = azurerm_subnet.example.id
  private_dns_zone_id    = azurerm_private_dns_zone.example.id
  sku_name               = "GP_Standard_D2ds_v4"

  depends_on = [azurerm_private_dns_zone_virtual_network_link.example]
}

# Variable pour le mot de passe admin MySQL
variable "mysql_admin_password" {
  description = "Le mot de passe pour l'utilisateur administrateur MySQL"
  type        = string
  sensitive   = true
}

# Sortie de la configuration kube pour se connecter au cluster AKS
output "kube_config" {
  value     = azurerm_kubernetes_cluster.main.kube_config_raw
  sensitive = true
}
