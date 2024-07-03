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

# Créer un serveur MySQL flexible
resource "azurerm_mysql_flexible_server" "mysql_instance" {
  name                = "ecom-mysql"
  location            = data.azurerm_resource_group.existing.location
  resource_group_name = data.azurerm_resource_group.existing.name
  sku_name            = "GP_Gen5_2"
  version             = "8.0"

  administrator_login          = "root"
  administrator_login_password = ""

  storage_profile {
    auto_grow = true  # Optionnel, activé par défaut
  }

  sku {
    capacity = 2  # Capacité en vCores, ajustez selon vos besoins
    tier     = "GeneralPurpose"
    family   = "Gen5"
  }

  ssl_enforcement {
    enabled     = true
    enforced    = true
    disabled_on = "2050-01-01"  # Date de désactivation, optionnel
  }

  public_network_access {
    enabled = true
  }
}

# Sortir la configuration kube pour se connecter au cluster AKS
output "kube_config" {
  value     = azurerm_kubernetes_cluster.main.kube_config_raw
  sensitive = true
}


