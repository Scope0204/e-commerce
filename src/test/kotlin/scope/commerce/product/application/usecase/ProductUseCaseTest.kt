package scope.commerce.product.application.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import scope.commerce.common.config.IntegrationTestBase
import scope.commerce.product.fixture.ProductFixture

class ProductUseCaseTest : IntegrationTestBase()  {
    @Autowired
    lateinit var queryProductUseCase: QueryProductUseCase

    @Autowired
    lateinit var productFixture: ProductFixture

    private val productCount = 5

    @BeforeEach
    fun setup() {
        productFixture.prepareProducts(productCount)
    }


    @Test
    @Transactional
    fun `단일 제품 조회`() {
        // given
        val products = productFixture.getAllSavedProducts()
        val targetProduct = products.first()
        val productId = targetProduct.id!!

        // when
        val result = queryProductUseCase.getProductById(productId)

        // then
        assertThat(result.productId).isEqualTo(productId)
        assertThat(result.name).isEqualTo(targetProduct.name)
        assertThat(result.price).isEqualTo(targetProduct.price)
        assertThat(result.quantity).isEqualTo(targetProduct.quantity)
    }

    @Test
    @Transactional
    fun `제품 목록 조회`() {
        // given
        val page = 1
        val size = 3

        // when
        val result = queryProductUseCase.getProducts(page, size)

        // then
        assertThat(result.products).hasSize(size)
        assertThat(result.totalElements).isEqualTo(productCount.toLong())
        assertThat(result.totalPages).isEqualTo((productCount + size - 1) / size)
    }

}