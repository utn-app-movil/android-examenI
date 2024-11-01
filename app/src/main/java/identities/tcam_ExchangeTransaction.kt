package cr.ac.utn.appmovil.identities

import identities.Identifier

data class tcam_ExchangeTransaction(
    override var Id: String,
    var persona: Persona,
    var fechaHora: String,
    var valorTipoCambio: Double,
    var montoCambiar: Double
) : Identifier() {

    // Cálculo del monto a pagar
    val montoAPagar: Double
        get() = montoCambiar * valorTipoCambio

    override val FullDescription: String
        get() = "Transacción de ${persona.FullDescription} el $fechaHora: $montoCambiar a $valorTipoCambio"
}
