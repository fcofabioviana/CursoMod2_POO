package aquario

import aquario.CoresPeixes.*
import aquario.TiposPeixes.*
import aquario.TamanhoPeixe.*
import aquario.TamanhoAquario.*
import kotlin.random.Random
import kotlin.random.nextInt

data class Peixe (val nome: String, val cor: String, val tamanho: String){
    override fun toString(): String {
        return "- Nome: $nome | Cor: $cor | Tamanho: $tamanho"
    }
}

fun main(){
    AdmAquario(TAMANHO_P.codTamanho, TAMANHO_P.capacidade, true)
    println("----- Sistema encerrado -----")
}

class AdmAquario (private var tamanho: Char, private var capacidade: Int, private var estaLimpo: Boolean) {

    private val infoAquario: String
        get() = """
            ==========================================
                      INFORMAÇÕES DO AQUÁRIO
            ==========================================
            Tamanho do aquário: $tamanho
            Capacidade total: $capacidade peixes
            Número de peixes do aquário: ${listaPeixes.size} peixes
            ------------------------------------------
            Listagem de peixes no aquário:
        """.trimIndent()

    private val listaPeixes: MutableList<Peixe> = mutableListOf()

    companion object {
        const val QTDE_PEIXES_AQUARIO_SUJO = 3
    }

    init {
        iniciaAquario()
    }

    private fun iniciaAquario() {
        do {
            println(menuAquario)

            val menu = readln().toIntOrNull() ?: 0

            when (menu) {
                1 -> {
                    if (!estaLimpo){
                        println("ATENÇÃO! Aquário sujo. Só poderá adicionar peixes após limpeza.")
                        return iniciaAquario()
                    } else if (listaPeixes.size == capacidade){
                        println("ATENÇÃO! Capacidade máxima do aquário atingida. Deseja fazer upgrade? [S/N]")
                        val upgrade = readln().uppercase()
                        if (upgrade == "S"){
                            fazerUpgrade()
                        } else {
                            iniciaAquario()
                        }
                        return iniciaAquario()
                    } else {
                        addPeixes()
                    }
                }
                2 -> alimentarPeixes()
                3 -> limparAquario()
                4 -> listarInfoAquario()
            }
        } while (menu != 0)
    }

    private fun addPeixes() {
        println(menuTiposPeixes)
        var especie = readln()
        when(especie){
            BETTA.cod -> especie = BETTA.nome
            BARBO.cod -> especie = BARBO.nome
            ACARA.cod -> especie = ACARA.nome
        }
        println(menuCoresPeixes)
        var cor = readln()
        when(cor) {
            AZUL.cod -> cor = AZUL.nome
            VERMELHO.cod -> cor = VERMELHO.nome
            AMARELO.cod -> cor = AMARELO.nome
        }
        println(menuTamanhoPeixe)
        var tamanho = readln()
        when(tamanho) {
            PEQUENO.cod -> tamanho = PEQUENO.nome
            MEDIO.cod -> tamanho = MEDIO.nome
            GRANDE.cod -> tamanho = GRANDE.nome
        }
        listaPeixes.add(Peixe(especie, cor, tamanho))
        if (listaPeixes.size % QTDE_PEIXES_AQUARIO_SUJO == 0){
            estaLimpo = false
        }
    }

    private fun alimentarPeixes(){
        if (listaPeixes.isEmpty()) {
            println("Aquário vazio. Não há peixes a serem alimentados.")
            return
        } else{
            when (val qtdePeixesAlimentados = Random.nextInt(0..listaPeixes.size)) {
                listaPeixes.size -> println("Sucesso! Temos ${listaPeixes.size} peixes no aquário e todos foram alimentados.")
                0 -> println("Que pena! Temos ${listaPeixes.size} peixe(s) no aquário mas nenhum se alimentou. Tente novamente.")
                else -> println("Atenção! Nem todos os peixes foram alimentados. " +
                        "\nTotal no aquário: ${listaPeixes.size}. Total alimentados: $qtdePeixesAlimentados.")
            }
        }
    }

    private fun limparAquario() {
        if (estaLimpo){
            println("Aquário ainda não precisa de limpeza.")
        } else {
            println("Limpeza do aquário realizada com sucesso.")
            estaLimpo = true
        }
    }

    private fun listarInfoAquario() {
        if (listaPeixes.isEmpty()){
            println(infoAquario)
            println("Aquário vazio. Adicione peixes.")
            println("==========================================")
        } else {
            println(infoAquario)
            listaPeixes.forEach { println(it) }
            println("==========================================")
        }
    }

    private fun fazerUpgrade(){
        when(tamanho){
            TAMANHO_P.codTamanho -> {
                tamanho = TAMANHO_M.codTamanho
                capacidade = TAMANHO_M.capacidade
            }
            TAMANHO_M.codTamanho -> {
                tamanho = TAMANHO_G.codTamanho
                capacidade = TAMANHO_G.capacidade
            }
            else -> println("Upgrade indisponível. Você já tem o maior modelo de aquário.")
        }
    }
}

val menuAquario = """
    -----------------------------------
    SISTEMA DE GERENCIAMENTO DO AQUARIO
    -----------------------------------
    Digite uma opção para a ação desejada:
    [1] -> ADICIONAR PEIXE
    [2] -> ALIMENTAR PEIXE
    [3] -> LIMPAR AQUARIO
    [4] -> LISTAR INFORMAÇÕES DO AQUÁRIO
    [0] -> FECHAR SISTEMA
""".trimIndent()

enum class TiposPeixes (val nome: String, val cod: String) {
    BETTA(nome = "Peixe Betta", cod = "1"),
    BARBO(nome = "Peixe Barbo", cod = "2"),
    ACARA(nome = "Peixe Acará", cod = "3");
}

val menuTiposPeixes = """
    Escolha a espécie:
    [${BETTA.cod}] -> ${BETTA.nome}
    [${BARBO.cod}] -> ${BARBO.nome}
    [${ACARA.cod}] -> ${ACARA.nome}
""".trimIndent()

enum class CoresPeixes (val nome: String, val cod: String) {
    AZUL(nome = "Azul", cod = "1"),
    VERMELHO(nome = "Vermelho", cod = "2"),
    AMARELO(nome = "Amarelo", cod = "3");
}

val menuCoresPeixes = """
    Escolha a cor:
    [${AZUL.cod}] -> ${AZUL.nome}
    [${VERMELHO.cod}] -> ${VERMELHO.nome}
    [${AMARELO.cod}] -> ${AMARELO.nome}
""".trimIndent()

enum class TamanhoPeixe (val nome: String, val cod: String) {
    PEQUENO(nome = "Pequeno", cod = "1"),
    MEDIO(nome = "Médio", cod = "2"),
    GRANDE(nome = "Grande", cod = "3");
}

val menuTamanhoPeixe = """
    Escolha o tamanho:
    [${PEQUENO.cod}] -> ${PEQUENO.nome}
    [${MEDIO.cod}] -> ${MEDIO.nome}
    [${GRANDE.cod}] -> ${GRANDE.nome}
""".trimIndent()

enum class TamanhoAquario (val codTamanho: Char, val nomeTamanho: String, val capacidade: Int) {
    TAMANHO_P(codTamanho = 'P', nomeTamanho = "Pequeno", capacidade = 5),
    TAMANHO_M(codTamanho = 'M', nomeTamanho = "Médio", capacidade = 10),
    TAMANHO_G(codTamanho = 'G', nomeTamanho = "Grande", capacidade = 15);
}
