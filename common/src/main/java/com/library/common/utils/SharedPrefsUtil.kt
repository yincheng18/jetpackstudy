package com.library.common.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import com.library.common.base.BaseApplication
import java.io.*

/**
 * @author yin
 * @desc
 * @time 2021/5/24
 */


/**
 *
 * @author bear
 *
 * SharedPrefereces工具类
 */
open class SharedPrefsUtil {
    companion object {
        const val SP_NAME = "dbName"
    }

    /**
     * 向SharedPreferences中写入int类型数据
     *
     * @param key 键
     * @param value 值
     */
    fun putValue(
        key: String?,
        value: Int
    ) {
        val sp = getEditor()
        sp.putInt(key, value)
        sp.commit()
    }

    /**
     * 向SharedPreferences中写入boolean类型的数据
     *
     * @param key 键
     * @param value 值
     */
    fun putValue(
        key: String?,
        value: Boolean
    ) {
        val sp = getEditor()
        sp.putBoolean(key, value)
        sp.commit()
    }

    /**
     * 向SharedPreferences中写入String类型的数据
     *
     * @param key 键
     * @param value 值
     */
    fun putValue(
        key: String?,
        value: String?
    ) {
        val sp = getEditor()
        sp.putString(key, value)
        sp.commit()
    }

    /**
     * 向SharedPreferences中写入float类型的数据
     *
     * @param name 对应的xml文件名称
     * @param key 键
     * @param value 值
     */
    fun putValue(
        key: String?,
        value: Float
    ) {
        val sp = getEditor()
        sp.putFloat(key, value)
        sp.commit()
    }

    /**
     * 向SharedPreferences中写入long类型的数据
     *
     * @param key 键
     * @param value 值
     */
    fun putValue(
        key: String?,
        value: Long
    ) {
        val sp = getEditor()
        sp.putLong(key, value)
        sp.commit()
    }

    /**
     * 从SharedPreferences中读取int类型的数据
     *
     * @param name 对应的xml文件名称
     * @param key 键
     * @param defValue 如果读取不成功则使用默认值
     * @return 返回读取的值
     */
    fun getValue(
        key: String?,
        defValue: Int
    ): Int {
        val sp =
            getSharedPreferences()
        return sp.getInt(key, defValue)
    }

    /**
     * 从SharedPreferences中读取boolean类型的数据
     *
     * @param key 键
     * @param defValue 如果读取不成功则使用默认值
     * @return 返回读取的值
     */
    fun getValue(
        key: String?,
        defValue: Boolean
    ): Boolean {
        val sp =
            getSharedPreferences()
        return sp.getBoolean(key, defValue)
    }

    /**
     * 从SharedPreferences中读取String类型的数据
     *
     * @param name 对应的xml文件名称
     * @param key 键
     * @param defValue 如果读取不成功则使用默认值
     * @return 返回读取的值
     */
    fun getValue(
        key: String?,
        defValue: String?
    ): String? {
        val sp =
            getSharedPreferences()
        return sp.getString(key, defValue)
    }

    /**
     * 从SharedPreferences中读取float类型的数据
     *
     * @param key 键
     * @param defValue 如果读取不成功则使用默认值
     * @return 返回读取的值
     */
    fun getValue(
        key: String?, defValue: Float
    ): Float {
        val sp =
            getSharedPreferences()
        return sp.getFloat(key, defValue)
    }

    /**
     * 从SharedPreferences中读取long类型的数据
     *
     * @param key 键
     * @param defValue 如果读取不成功则使用默认值
     * @return 返回读取的值
     */
    fun getValue(
        key: String?, defValue: Long
    ): Long {
        val sp =
            getSharedPreferences()
        return sp.getLong(key, defValue)
    }

    /**
     * 针对复杂类型存储<对象>
     *
     * @param key
     * @param object
    </对象> */
    open fun setObject(key: String?, value: Any?) {
        val sp: SharedPreferences =
            getSharedPreferences()

        //创建字节输出流
        val baos = ByteArrayOutputStream()
        //创建字节对象输出流
        var out: ObjectOutputStream? = null
        try {
            //然后通过将字对象进行64转码，写入key值为key的sp中
            out = ObjectOutputStream(baos)
            out.writeObject(value)
            val objectVal: String = String(Base64.encode(baos.toByteArray(), Base64.DEFAULT))
            val editor = sp.edit()
            editor.putString(key, objectVal)
            editor.apply()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                baos.close()
                out?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    open fun <T> getObject(key: String?, clazz: Class<Any>?): Any? {
        val sp: SharedPreferences =
            getSharedPreferences()
        if (sp.contains(key)) {
            val objectVal = sp.getString(key, null)
            val buffer: ByteArray = Base64.decode(objectVal, Base64.DEFAULT)
            //一样通过读取字节流，创建字节流输入流，写入对象并作强制转换
            val bais = ByteArrayInputStream(buffer)
            var ois: ObjectInputStream? = null
            try {
                ois = ObjectInputStream(bais)
                return ois.readObject()
            } catch (e: StreamCorruptedException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } finally {
                try {
                    bais.close()
                    ois?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return null
    }

    //获取Editor实例
    private fun getEditor(name: String = SP_NAME): SharedPreferences.Editor {
        return getSharedPreferences(name).edit()
    }

    //获取SharedPreferences实例
    private fun getSharedPreferences(name: String = SP_NAME): SharedPreferences {
        return BaseApplication.context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }
}