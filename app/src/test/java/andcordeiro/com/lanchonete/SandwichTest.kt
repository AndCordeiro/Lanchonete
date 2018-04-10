package andcordeiro.com.lanchonete

import andcordeiro.com.lanchonete.entities.Ingredient
import andcordeiro.com.lanchonete.histories.menu.MenuModelImpl
import junit.framework.Assert
import org.junit.Test

class SandwichTest{

    companion object {
        var lettuce: Ingredient? = Ingredient(1, "Alface", 0.40, "https://goo.gl/9DhCgk")
        var bacon: Ingredient? = Ingredient(2, "Bacon", 2.00, "https://goo.gl/8qkVH0")
        var hamburguer: Ingredient? = Ingredient(3, "Hamburguer de Carne", 3.00, "https://goo.gl/U01SnT")
        var egg: Ingredient? = Ingredient(4, "Ovo", 0.80, "https://goo.gl/weL1Rj")
        var cheese: Ingredient? = Ingredient(5, "Queijo", 1.50, "https://goo.gl/D69Ow2")
        var pao: Ingredient? = Ingredient(6, "Pão com gergelim", 1.00, "https://goo.gl/evgjyj")

    }

    @Test
    fun priceXBaconTest(){
        val ingredients: List<Ingredient>? = ArrayList<Ingredient>()
        ingredients as MutableList
        ingredients.add(Companion.pao!!)
        ingredients.add(Companion.bacon!!)
        ingredients.add(Companion.hamburguer!!)
        ingredients.add(Companion.cheese!!)

        val model = MenuModelImpl()
        val output: Double? = 7.5
        Assert.assertEquals(output, model.priceSandwich(ingredients))
    }

    @Test
    fun priceXBurguerTest(){
        val ingredients: List<Ingredient>? = ArrayList<Ingredient>()
        ingredients as MutableList
        ingredients.add(Companion.pao!!)
        ingredients.add(Companion.hamburguer!!)
        ingredients.add(Companion.cheese!!)

        val model = MenuModelImpl()
        val output: Double? = 5.5
        Assert.assertEquals(output, model.priceSandwich(ingredients))
    }

    @Test
    fun priceXEggTest(){
        val ingredients: List<Ingredient>? = ArrayList<Ingredient>()
        ingredients as MutableList
        ingredients.add(Companion.pao!!)
        ingredients.add(Companion.egg!!)
        ingredients.add(Companion.hamburguer!!)
        ingredients.add(Companion.cheese!!)

        val model = MenuModelImpl()
        val output: Double? = 6.3
        Assert.assertEquals(output, model.priceSandwich(ingredients))
    }

    @Test
    fun priceXEggBaconTest(){
        val ingredients: List<Ingredient>? = ArrayList<Ingredient>()
        ingredients as MutableList
        ingredients.add(Companion.pao!!)
        ingredients.add(Companion.lettuce!!)
        ingredients.add(Companion.egg!!)
        ingredients.add(Companion.bacon!!)
        ingredients.add(Companion.hamburguer!!)
        ingredients.add(Companion.cheese!!)

        val model = MenuModelImpl()
        val output: Double? = 8.7
        Assert.assertEquals(output, model.priceSandwich(ingredients))
    }

    @Test
    fun priceWithLettuceWithoutBaconTest(){
        val ingredients: List<Ingredient>? = ArrayList<Ingredient>()
        ingredients as MutableList
        ingredients.add(Companion.pao!!)
        ingredients.add(Companion.lettuce!!)
        ingredients.add(Companion.hamburguer!!)
        ingredients.add(Companion.hamburguer!!)
        ingredients.add(Companion.cheese!!)
        ingredients.add(Companion.cheese!!)

        val model = MenuModelImpl()
        val output: Double? = 9.36
        Assert.assertEquals(output, model.priceSandwich(ingredients))
    }

    @Test
    fun priceTripleMeatTest(){
        val ingredients: List<Ingredient>? = ArrayList<Ingredient>()
        ingredients as MutableList
        ingredients.add(Companion.pao!!)
        ingredients.add(Companion.bacon!!)
        ingredients.add(Companion.hamburguer!!)
        ingredients.add(Companion.hamburguer!!)
        ingredients.add(Companion.hamburguer!!)
        ingredients.add(Companion.cheese!!)

        val model = MenuModelImpl()
        val output: Double? = 10.5
        Assert.assertEquals(output, model.priceSandwich(ingredients))
    }

    @Test
    fun priceTripleCheeseTest(){
        val ingredients: List<Ingredient>? = ArrayList<Ingredient>()
        ingredients as MutableList
        ingredients.add(Companion.pao!!)
        ingredients.add(Companion.bacon!!)
        ingredients.add(Companion.hamburguer!!)
        ingredients.add(Companion.cheese!!)
        ingredients.add(Companion.cheese!!)
        ingredients.add(Companion.cheese!!)

        val model = MenuModelImpl()
        val output: Double? = 9.0
        Assert.assertEquals(output, model.priceSandwich(ingredients))
    }

    @Test
    fun priceTriplePromotionInsaneBurguerTest(){
        val ingredients: List<Ingredient>? = ArrayList<Ingredient>()
        ingredients as MutableList
        ingredients.add(Companion.pao!!)
        ingredients.add(Companion.lettuce!!)
        ingredients.add(Companion.hamburguer!!)
        ingredients.add(Companion.hamburguer!!)
        ingredients.add(Companion.hamburguer!!)
        ingredients.add(Companion.hamburguer!!)
        ingredients.add(Companion.cheese!!)
        ingredients.add(Companion.cheese!!)
        ingredients.add(Companion.cheese!!)
        ingredients.add(Companion.cheese!!)
        ingredients.add(Companion.cheese!!)
        ingredients.add(Companion.cheese!!)

        val model = MenuModelImpl()
        val output: String? = "14,76"
        Assert.assertEquals(output, "%.2f".format(model.priceSandwich(ingredients)))
    }

}