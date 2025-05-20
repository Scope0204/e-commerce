package scope.commerce.product.infra.repository.redis

import org.springframework.data.redis.core.RedisOperations
import org.springframework.data.redis.core.SessionCallback

object RedisTransaction {
    fun transaction(operations: RedisOperations<String, String>, command: (operation: RedisOperations<String, String>) -> Unit) {
        operations.execute(object : SessionCallback<Void?> {
            override fun <K, V> execute(callbackOperations: RedisOperations<K, V>): Void? {
                callbackOperations.multi() // 트랜잭션 시작
                command.invoke(operations) // 사용자 정의 명령 실행
                callbackOperations.exec() // 트랜잭션 커밋
                return null
            }
        })
    }
}