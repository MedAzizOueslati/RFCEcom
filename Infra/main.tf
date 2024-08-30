# Define the Azure provider
provider "azurerm" {
  features {}
  use_msi = true
  subscription_id = "460bc14f-4852-44b0-809b-ce133bada6c4"
}

# Use the existing resource group
data "azurerm_resource_group" "existing" {
  name = "RFC_Ecom"
}

# Use the existing Azure container registry
data "azurerm_container_registry" "existing" {
  name                = "EcomRegistr"
  resource_group_name = data.azurerm_resource_group.existing.name
}

# Create an AKS cluster
resource "azurerm_kubernetes_cluster" "existing" {
  name                = "EcomCluster"
  location            = data.azurerm_resource_group.existing.location
  resource_group_name = data.azurerm_resource_group.existing.name
  dns_prefix          = "myEcomcluster"

  default_node_pool {
    name       = "default"
    node_count = 3  # Updated to use 3 nodes
    vm_size    = "Standard_DS2_v2"
  }

  identity {
    type = "SystemAssigned"
  }

  network_profile {
    network_plugin = "azure"
    network_policy = "azure"
  }

 ingress_application_gateway {
    effective_gateway_id = "/subscriptions/460bc14f-4852-44b0-809b-ce133bada6c4/resourceGroups/MC_RFC_Ecom_EcomCluster_northeurope/providers/Microsoft.Network/applicationGateways/ingress-appgateway"
    gateway_name         = "ingress-appgateway"
    ingress_application_gateway_identity = [
      {
        user_assigned_identity_id = "/subscriptions/460bc14f-4852-44b0-809b-ce133bada6c4/resourcegroups/MC_RFC_Ecom_EcomCluster_northeurope/providers/Microsoft.ManagedIdentity/userAssignedIdentities/ingressapplicationgateway-ecomcluster"
      },
    ]
  }

  tags = {
    environment = "EcomRFC"
  }

}


# Define the Application Gateway resource separately
resource "azurerm_application_gateway" "existing" {
  name                = "ingress-appgateway"
  resource_group_name = "MC_RFC_Ecom_EcomCluster_northeurope"
  location            = "North Europe"
  # Other configurations needed for the application gateway
  lifecycle {
    prevent_destroy = true
  }
}

