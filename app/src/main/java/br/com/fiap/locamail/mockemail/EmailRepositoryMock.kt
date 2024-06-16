package br.com.fiap.locamail.mockemail

interface EmailRepositoryMock {
    suspend fun getAllEmails(): List<EmailMock>
}