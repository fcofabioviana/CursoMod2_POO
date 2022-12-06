import desafioEstoquePecas.MenuEditarItens.*
import desafioEstoquePecas.MenuPrincipal.*
import desafioEstoquePecas.menuEditarItens
import desafioEstoquePecas.menuPrincipal

fun main(){
    EstoquePecas()
    println("----- Sistema encerrado -----")
}

data class Item (val id: Int, var nomeItem: String, var quantidade: Int){
    override fun toString(): String {
        return "#0$id | $nomeItem | $quantidade"
    }
}

class EstoquePecas {

    private var id = 0
    private var codItemAlteracao = 0
    private val listaItens: MutableList<Item> = mutableListOf()

    private val cabecalhoItensEstoque = """
        ------------------------
        ID | Item | Quantidade
        ------------------------
""".trimIndent()

    private val mensagemEstoqueVazio = """
        ------------------------
        Não há itens em estoque.
""".trimIndent()


    init {
        inicioPrograma()
    }

    private fun idItem(): Int {
        id++
        return id
    }

    private fun inserirItemEstoque (){
        println("Digite o nome do item a ser adicionado:")
        val produto = readln()
        println("Digite a quantidade do item que está sendo adicionado:")
        val qtdeProduto = readln().toIntOrNull() ?: 0
        if (qtdeProduto > 999 || qtdeProduto < 0) {
            throw QuantidadeEstoqueException()
        }
        listaItens.add(Item(idItem(), produto, qtdeProduto))
    }

    private fun editarItem(){
        if (listaItens.isEmpty()){
            println(mensagemEstoqueVazio)
            println("------------------------")
        } else {
            println("Informe o código do item a ser modificado:")
            codItemAlteracao = readln().toIntOrNull() ?: 0
            if (codItemAlteracao > listaItens.size || codItemAlteracao <= 0){
                println("Item inexistente! Digite um código válido.\n")
                return
            } else{
                println(menuEditarItens)
                val alteraProduto = readln().toIntOrNull() ?: 0
                if (alteraProduto < ALTERA_NOME.cod || alteraProduto > ALTERA_NOME_QTDE.cod){
                    println("Opção inválida! Não foi possível alterar o item.\n")
                    return
                }
                when (alteraProduto){
                    ALTERA_NOME.cod -> alteraNomeItem()
                    ALTERA_QTDE.cod -> alteraQtdeItem()
                    ALTERA_NOME_QTDE.cod -> {
                        alteraNomeItem()
                        alteraQtdeItem()
                    }
                }
            }
        }
    }

    private fun alteraNomeItem(){
        println("Informe o novo nome para o produto de código #0$codItemAlteracao:")
        val novoProduto = readln()//tratar aqui
        listaItens[codItemAlteracao - 1] = listaItens [codItemAlteracao - 1].copy(nomeItem = novoProduto)
    }

    private fun alteraQtdeItem(){
        println("Informe a nova quantidade para o produto de código #0$codItemAlteracao:")
        val novaQtde = readln().toIntOrNull() ?: 0 //tratar aqui
        listaItens[codItemAlteracao - 1] = listaItens [codItemAlteracao - 1].copy(quantidade = novaQtde)
    }

    private fun exibirItensEstoque() {
        if(listaItens.all { it.quantidade == 0 }){
            println(mensagemEstoqueVazio)
        } else {
            val comEstoque = listaItens.filter { it.quantidade > 0 }
            println(cabecalhoItensEstoque)
            comEstoque.forEach {
                println(it)
            }
        }
        println("------------------------\n")
    }

    private fun exibirTodosItens() {
        if (listaItens.isEmpty()) {
            println(mensagemEstoqueVazio)
        } else {
            println(cabecalhoItensEstoque)
            listaItens.forEach {
                println(it)
            }
        }
        println("------------------------\n")
    }

    fun inicioPrograma() {
        println("------------------------------")
        println("SISTEMA DE CONTROLE DE ESTOQUE")
        println("------------------------------")
        do {
            println(menuPrincipal)

            val menu = readln().toIntOrNull() ?: 0

            when (menu) {
                ADDITEM.cod -> inserirItemEstoque()
                EDITARITEM.cod -> editarItem()
                EXIBIRITENS.cod -> exibirItensEstoque()
                EXIBIRTODOS.cod -> exibirTodosItens()
            }
        } while (menu != FECHAR.cod)
    }
}

class QuantidadeEstoqueException : Exception(){
    override fun getLocalizedMessage(): String {
        return "Quantidade inválida! Quantidade mínima 0 e máxima 999."
    }
}