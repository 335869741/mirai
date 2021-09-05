/*
 * Copyright 2019-2021 Mamoe Technologies and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AGPLv3 license that can be found through the following link.
 *
 * https://github.com/mamoe/mirai/blob/dev/LICENSE
 */

package net.mamoe.mirai.mock.contact

import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.contact.MemberPermission

public interface MockMember : Member, MockContact {
    override var nick: String
    override var remark: String
    override val group: MockGroup

    // Mock api, no event broadcast
    override var permission: MemberPermission
    public fun modifyNameCard(value: String)
    public fun modifySpecialTitle(value: String)

}