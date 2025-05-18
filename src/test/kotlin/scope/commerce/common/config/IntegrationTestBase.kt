package scope.commerce.common.config
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest
abstract class IntegrationTestBase {

    // MySQLContainer 설정 추가
    companion object {
        @Container
        private val mysqlContainer = MySQLContainer("mysql:8.0").apply {
            withDatabaseName("test_db")
            withUsername("test_user")
            withPassword("1111")
            withReuse(false) // 매 테스트마다 새 컨테이너를 사용하는 옵션
        }

        @JvmStatic
        @DynamicPropertySource
        fun overrideProps(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.username", mysqlContainer::getUsername)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
            registry.add("spring.datasource.driver-class-name", mysqlContainer::getDriverClassName)
        }
    }
}
