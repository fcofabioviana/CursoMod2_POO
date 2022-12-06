package desafioEstoquePecas

import desafioEstoquePecas.MenuEditarItens.*
import desafioEstoquePecas.MenuPrincipal.*

enum class MenuPrincipal (val nome: String, val cod: Int) {
    ADDITEM(nome = "ADICIONAR ITEM", cod = 1),
    EDITARITEM(nome = "EDITAR ITEM", cod = 2),
    EXIBIRITENS(nome = "EXIBIR ITENS EM ESTOQUE", cod = 3),
    EXIBIRTODOS(nome = "EXIBIR TODOS OS ITENS", cod = 4),
    FECHAR(nome = "FECHAR SISTEMA", cod = 0);
}

val menuPrincipal = """
    Digite a opção desejada no Menu:
    ${ADDITEM.cod}..............${ADDITEM.nome}
    ${EDITARITEM.cod}..............${EDITARITEM.nome}
    ${EXIBIRITENS.cod}..............${EXIBIRITENS.nome}
    ${EXIBIRTODOS.cod}..............${EXIBIRTODOS.nome}
    ${FECHAR.cod}......${FECHAR.nome} 
""".trimIndent()

enum class MenuEditarItens (val nome: String, val cod: Int) {
    ALTERA_NOME(nome = "Altera apenas o nome do item.", cod = 1),
    ALTERA_QTDE(nome = "Altera apenas a quantidade em estoque do item.", cod = 2),
    ALTERA_NOME_QTDE(nome = "Altera nome e quantidade do item.", cod = 3);
}

val menuEditarItens = """
    Digite uma opção para o tipo de alteração que deseja realizar:
    [${ALTERA_NOME.cod}] -> ${ALTERA_NOME.nome}
    [${ALTERA_QTDE.cod}] -> ${ALTERA_QTDE.nome}
    [${ALTERA_NOME_QTDE.cod}] -> ${ALTERA_NOME_QTDE.nome}
""".trimIndent()
