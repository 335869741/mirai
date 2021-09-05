/*
 * Copyright 2019-2021 Mamoe Technologies and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AGPLv3 license that can be found through the following link.
 *
 * https://github.com/mamoe/mirai/blob/dev/LICENSE
 */

package net.mamoe.mirai.mock.utils

import java.util.concurrent.atomic.AtomicInteger

public interface NameGenerator {
    public fun nextGroupName(): String
    public fun nextFriendName(): String

    public companion object {
        @JvmField
        public val DEFAULT: NameGenerator = SimpleNameGenerator()
    }
}

public class SimpleNameGenerator : NameGenerator {
    private val groupCounter = AtomicInteger(0)
    private val friendCounter = AtomicInteger(0)

    override fun nextGroupName(): String = "Testing Group #" + groupCounter.getAndIncrement()
    override fun nextFriendName(): String = "Testing Friend #" + friendCounter.getAndIncrement()
}
