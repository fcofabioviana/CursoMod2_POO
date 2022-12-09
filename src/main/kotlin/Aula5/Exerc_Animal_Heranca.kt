open class Animal(val classe: String)

open class Quadrupede(classe: String, val nome: String, val habitat: String, val tamanho: String) : Animal(classe){
    open val tipoQuadrupede = "desconhecido"
    open fun comer() {
        println("O $nome comeu.")
    }
    open fun beber() {
        println("O $nome bebeu.")
    }

    override fun toString(): String {
        return """
            O $tipoQuadrupede de nome $nome é um $classe $habitat de tamanho $tamanho.
        """.trimIndent()
    }
}

class Cachorro(classe: String, nome: String, habitat: String, tamanho: String) : Quadrupede(classe, nome, habitat, tamanho){
    override val tipoQuadrupede = "cachorro"
    override fun comer() {
        println("O $tipoQuadrupede de nome $nome comeu.")
    }
    override fun beber() {
        println("O $tipoQuadrupede de nome $nome bebeu.")
    }
}

class Gato(classe: String, nome: String, habitat: String, tamanho: String) : Quadrupede(classe, nome, habitat, tamanho){
    override val tipoQuadrupede = "gato"
    override fun comer() {
        println("O $tipoQuadrupede de nome $nome comeu.")
    }
    override fun beber() {
        println("O $tipoQuadrupede de nome $nome bebeu.")
    }

}

fun main () {
    val listaAnimais: MutableList<Quadrupede> = mutableListOf()
    val cachorro = Cachorro(classe = "mamífero", nome = "Golias", habitat = "doméstico", tamanho = "grande")
    listaAnimais.add(cachorro)
    val gato = Gato(classe = "mamífero", nome = "Tom", habitat = "doméstico", tamanho = "pequeno")
    listaAnimais.add(gato)


    cachorro.beber()
    println()
    gato.comer()
    println()
    listaAnimais.forEach {
        println(it)
    }
}
