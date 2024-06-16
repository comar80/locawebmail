package br.com.fiap.locamail.mockemail

// MockSearchViewModel.kt
class MockSearchViewModel : SearchViewModelMock(MockEmailRepository()) {
    init {
        // Inicializa com dados de exemplo
        _emailList.value = listOf(
            EmailMock(1, "Assunto 1", "Corpo do email 1", listOf("destinatario1@example.com", "destinatario2@example.com")),
            EmailMock(2, "Assunto 2", "Corpo do email 2", listOf("destinatario1@example.com")),
            EmailMock(3, "Assunto 3", "Corpo do email 3", listOf("destinatario4@example.com", "destinatario5@example.com", "destinatario6@example.com"))
        )
    }
}
