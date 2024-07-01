# Définir le fournisseur Azure
provider "azurerm" {
  features {}
  use_msi = true
  subscription_id = "460bc14f-4852-44b0-809b-ca6c4"

  // Si vous avez besoin de spécifier l'object_id explicitement, utilisez plutôt le bloc service_principal
  service_principal {
    object_id = "9d909205-db22-4faa-af87-54cc824f14d4"
  }
}


# Utiliser le groupe de ressources existant
data "azurerm_resource_group" "existing" {
  name = "RFC-Ecom"
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

#   tags = {
#     environment = "Development"
#   }
}

# Sortir la configuration kube pour se connecter au cluster AKS
output "kube_config" {
  value     = azurerm_kubernetes_cluster.main.kube_config_raw
  sensitive = true
}
