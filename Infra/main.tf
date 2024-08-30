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

