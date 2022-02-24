/*
 * Copyright 2019-2021 Mamoe Technologies and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AGPLv3 license that can be found through the following link.
 *
 * https://github.com/mamoe/mirai/blob/dev/LICENSE
 */

package net.mamoe.mirai.console.internal.data.builtins

import kotlinx.coroutines.CoroutineScope
import net.mamoe.mirai.console.MiraiConsoleImplementation
import net.mamoe.mirai.console.data.AutoSavePluginDataHolder
import net.mamoe.mirai.console.data.PluginConfig
import net.mamoe.mirai.console.data.PluginData
import net.mamoe.mirai.console.data.PluginDataStorage
import net.mamoe.mirai.utils.childScope
import net.mamoe.mirai.utils.minutesToMillis
import kotlin.coroutines.CoroutineContext


internal class ConsoleDataScopeImpl(
    parentCoroutineContext: CoroutineContext,
    private val dataStorage: PluginDataStorage,
    private val configStorage: PluginDataStorage,
) : CoroutineScope by parentCoroutineContext.childScope("ConsoleDataScope"), MiraiConsoleImplementation.ConsoleDataScope {
    override val dataHolder: AutoSavePluginDataHolder = ConsoleBuiltInPluginDataHolder(this.coroutineContext)
    override val configHolder: AutoSavePluginDataHolder = ConsoleBuiltInPluginConfigHolder(this.coroutineContext)

    private val data: List<PluginData> = mutableListOf()
    private val configs: MutableList<PluginConfig> = mutableListOf(
        AutoLoginConfig,
        PluginDependenciesConfig,
    )

    override fun addAndReloadConfig(config: PluginConfig) {
        configs.add(config)
        configStorage.load(configHolder, config)
    }

    override fun reloadAll() {
        data.forEach { dt ->
            dataStorage.load(dataHolder, dt)
        }
        configs.forEach { config ->
            configStorage.load(dataHolder, config)
        }
    }
}

private class ConsoleBuiltInPluginDataHolder(
    parentCoroutineContext: CoroutineContext
) : AutoSavePluginDataHolder,
    CoroutineScope by parentCoroutineContext.childScope("ConsoleBuiltInPluginDataHolder") {
    override val autoSaveIntervalMillis: LongRange = 1.minutesToMillis..10.minutesToMillis
    override val dataHolderName: String get() = "Console"
}

private class ConsoleBuiltInPluginConfigHolder(
    parentCoroutineContext: CoroutineContext
) : AutoSavePluginDataHolder,
    CoroutineScope by parentCoroutineContext.childScope("ConsoleBuiltInPluginConfigHolder") {
    override val autoSaveIntervalMillis: LongRange = 1.minutesToMillis..10.minutesToMillis
    override val dataHolderName: String get() = "Console"
}