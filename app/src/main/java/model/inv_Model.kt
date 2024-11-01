package model

import identities.Identifier

data class inv_Model(
    val personId: String,
    val personName: String,
    val personEmail: String,
    val productCode: String,
    val productName: String,
    val productQuantity: Int,
    val entryDateTime: String,
    val providerName: String,
    val unitCost: Double
) : Identifier() {
    override val FullDescription: String
        get() = "$productName - $productCode"
}
