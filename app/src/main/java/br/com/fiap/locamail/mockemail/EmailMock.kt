package br.com.fiap.locamail.mockemail

data class EmailMock(
    val id: Int,
    val subject: String,
    val body: String,
    val recipients: List<String>
)
