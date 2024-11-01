package cr.ac.utn.appmovil.models

import cr.ac.utn.appmovil.identities.tcam_ExchangeTransaction

class tcam_ExchangeTransactionModel {
    private val transactions = mutableListOf<tcam_ExchangeTransaction>()

    fun addTransaction(transaction: tcam_ExchangeTransaction) {
        // Verifica si ya existe una transacción con el mismo ID
        if (transactions.any { it.Id == transaction.Id }) {
            throw IllegalArgumentException("Transacción ya existe")
        }
        transactions.add(transaction)
    }

    fun updateTransaction(updatedTransaction: tcam_ExchangeTransaction) {
        val index = transactions.indexOfFirst { it.Id == updatedTransaction.Id }
        if (index != -1) {
            transactions[index] = updatedTransaction
        } else {
            throw IllegalArgumentException("Transacción no encontrada")
        }
    }

    fun deleteTransaction(id: String) {
        transactions.removeIf { it.Id == id }
    }

    fun getAllTransactions(): List<tcam_ExchangeTransaction> {
        return transactions
    }
}
