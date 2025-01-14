package ltd.guimc.lgzbot.utils

import ltd.guimc.lgzbot.PluginMain.logger
import ltd.guimc.lgzbot.utils.AsciiUtil.sbc2dbcCase

object RegexUtils {
    // 获取正则表达式列表
    fun getDefaultRegex(): Array<Regex> {
        // Read from resources regex.txt
        val regexFile = RegexUtils::class.java.getResourceAsStream("/regex.txt")
        val regexList = mutableListOf<Regex>()
        regexFile?.bufferedReader()?.use { reader ->
            reader.lines().forEach {
                // regexList.add(Regex(PinyinUtils.convertToPinyin(it)))
                regexList.add(Regex(it))
            }
        }

        return regexList.toTypedArray()
    }

    fun getDefaultPinyinRegex(): Array<Regex> {
        // Read from resources regex.txt
        val regexFile = RegexUtils::class.java.getResourceAsStream("/regex.txt")
        val regexList = mutableListOf<Regex>()
        regexFile?.bufferedReader()?.use { reader ->
            reader.lines().forEach {
                // regexList.add(Regex(PinyinUtils.convertToPinyin(it)))
                regexList.add(Regex(PinyinUtils.convertToPinyin(it)))
            }
        }

        return regexList.toTypedArray()
    }

    // 匹配正则表达式列表 返回是否匹配
    fun matchRegex(regexList: Array<Regex>, message: String): Boolean {
        try {
            var i = 0
            val unPeekText = sbc2dbcCase(message).lowercase()
                .replace(" ", "")
                .replace(",", "")
                .replace(".", "")
                .replace("!", "")
                .replace("?", "")
                .replace(";", "")
                .replace(":", "")
                .replace("\"", "")
                .replace("'", "")
                .replace("“", "")
                .replace("”", "")
                .replace("‘", "")
                .replace("’", "")
                .replace("<", "")
                .replace(">", "")
                .replace("(", "")
                .replace(")", "")
                .replace("內", "内")
            for (regex in regexList) {
                i++
                if (regex.containsMatchIn(unPeekText)) {
                    logger.info("匹配成功 在第${i}行 ${regex.find(unPeekText)?.value}")
                    return true
                }
            }
        } catch (_: Throwable) {}
        return false
    }

    fun matchRegexPinyin(regexList: Array<Regex>, message: String): Boolean {
        try {
            var i = 0
            val unPeekText = PinyinUtils.convertToPinyin(sbc2dbcCase(message)).lowercase()
                .replace(" ", "")
                .replace(",", "")
                .replace(".", "")
                .replace("!", "")
                .replace("?", "")
                .replace(";", "")
                .replace(":", "")
                .replace("\"", "")
                .replace("'", "")
                .replace("“", "")
                .replace("”", "")
                .replace("‘", "")
                .replace("’", "")
                .replace("<", "")
                .replace(">", "")
                .replace("(", "")
                .replace(")", "")
                .replace("內", "内")
            for (regex in regexList) {
                i++
                if (regex.containsMatchIn(unPeekText)) {
                    logger.info("匹配成功 在第${i}行 ${regex.find(unPeekText)?.value}")
                    return true
                }
            }
        } catch (_: Throwable) {}
        return false
    }

    // 匹配正则表达式 返回匹配结果
    fun matchRegex(regex: String, message: String): String? {
        logger.info("匹配成功")
        return Regex(regex).find(message)?.groups?.get(0)?.value
    }
}
