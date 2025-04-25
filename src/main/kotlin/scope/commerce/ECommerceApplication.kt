package scope.commerce

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class ECommerceApplication

fun main(args: Array<String>) {
	runApplication<ECommerceApplication>(*args)
}
