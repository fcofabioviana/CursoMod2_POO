import desafioEstoquePecas.MenuEditarItens.*
import desafioEstoquePecas.MenuPrincipal.*
import desafioEstoquePecas.menuEditarItens
import desafioEstoquePecas.menuPrincipal

fun main(){
    val iniciar = EstoquePecas()
    iniciar.inicioPrograma()
    println("----- Sistema encerrado -----")
}

data class Item (val id: Int, var nomeItem: String, var quantidade: Int){
    override fun toString(): String {
        return "#0$id | $nomeItem | $quantidade"
    }
}

val cabecalhoItensEstoque = """
        ------------------------
        ID | Item | Quantidade
        ------------------------
""".trimIndent()

class EstoquePecas {

    private val listaItens: MutableList<Item> = mutableListOf()

    fun inserirItemEstoque (){
        println("Digite o produto a ser adicionado:")
        val produto = readln()
        println("Digite a quantidade do produto que está sendo adicionado:")
        val qtdeProduto = readln().toIntOrNull() ?: 0
        listaItens.add(Item((listaItens.size + 1), produto, qtdeProduto))
    }

    fun editarItem(){
        if (listaItens.isEmpty()){
            println("Não há itens no estoque.")
        } else {
            println("Informe o código do item a ser modificado:")
            val codItem = readln().toIntOrNull() ?: 0
            if (codItem > listaItens.size || codItem <= 0){
                println("Item não existe no estoque. Digite um código válido.\n")
                return
            } else{
                println(menuEditarItens)
                val alteraProduto = readln().toIntOrNull() ?: 0
                if (alteraProduto <= 0 || alteraProduto > 3){
                    println("Opção inválida! Não foi possível alterar o item.\n")
                    return
                }
                when (alteraProduto){
                    ALTERA_NOME.cod -> {
                        println("Informe o novo nome para o produto de código #0$codItem:")
                        val novoProduto = readln()//tratar aqui
                        listaItens[codItem - 1] = listaItens [codItem - 1].copy(nomeItem = novoProduto)
                    }
                    ALTERA_QTDE.cod -> {
                        println("Informe a nova quantidade para o produto de código #0$codItem:")
                        val novaQtde = readln().toIntOrNull() ?: 0 //tratar aqui
                        listaItens[codItem - 1] = listaItens [codItem - 1].copy(quantidade = novaQtde)
                    }
                    ALTERA_NOME_QTDE.cod -> {
                        println("Informe o novo nome para o produto de código #0$codItem:")
                        val novoProduto = readln() //tratar aqui
                        listaItens[codItem - 1] = listaItens [codItem - 1].copy(nomeItem = novoProduto)
                        println("Informe a nova quantidade para o produto de código #0$codItem:")
                        val novaQtde = readln().toIntOrNull() ?: 0 //tratar aqui
                        listaItens[codItem - 1] = listaItens [codItem - 1].copy(quantidade = novaQtde)
                    }
                }
            }
        }
    }

    fun imprimirItens () {
        println("------------------------")
        println("ID | Item | Quantidade")
        println("------------------------")
        val comEstoque = listaItens.filter { it.quantidade > 0 }
        if (comEstoque.isNotEmpty()){
            comEstoque.forEach {
                println(it)
            }
        } else {
            listaItens.forEach {
                println(it)
            }
        }
        println("------------------------\n")
    }

    private fun exibirItensEstoque() {
        if(listaItens.all { it.quantidade == 0 }){
            println("Não há itens em estoque.\n")
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
            println("Não há itens em estoque.\n")
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