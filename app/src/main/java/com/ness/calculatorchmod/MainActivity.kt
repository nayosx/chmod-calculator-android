package com.ness.calculatorchmod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var ownerRead: Switch
    private lateinit var ownerWrite: Switch
    private lateinit var ownerExec: Switch

    private lateinit var groupRead: Switch
    private lateinit var groupWrite: Switch
    private lateinit var groupExec: Switch

    private lateinit var publicRead: Switch
    private lateinit var publicWrite: Switch
    private lateinit var publicExec: Switch

    private lateinit var permissionAbsolute: TextView
    private lateinit var permissionSymbolic: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        ownerRead = findViewById(R.id.switch1)
        ownerWrite = findViewById(R.id.switch2)
        ownerExec = findViewById(R.id.switch3)

        groupRead = findViewById(R.id.switch4)
        groupWrite = findViewById(R.id.switch5)
        groupExec = findViewById(R.id.switch6)

        publicRead = findViewById(R.id.switch7)
        publicWrite = findViewById(R.id.switch8)
        publicExec = findViewById(R.id.switch9)

        permissionAbsolute = findViewById(R.id.textAbsoluteMode)
        permissionSymbolic = findViewById(R.id.textSymbolicMode)
    }

    fun calculate(v: View) {
        val ownerAbsolute: Int = getPermissionAbsoluteMode(ownerRead.isChecked, ownerWrite.isChecked, ownerExec.isChecked)
        val groupAbsolute: Int = getPermissionAbsoluteMode(groupRead.isChecked, groupWrite.isChecked, groupExec.isChecked)
        val publicAbsolute: Int = getPermissionAbsoluteMode(publicRead.isChecked, publicWrite.isChecked, publicExec.isChecked)

        val ownerSymbolic: String = getPermissionSymbolicMode(ownerRead.isChecked, ownerWrite.isChecked, ownerExec.isChecked)
        val groupSymbolic: String = getPermissionSymbolicMode(groupRead.isChecked, groupWrite.isChecked, groupExec.isChecked)
        val publicSymbolic: String = getPermissionSymbolicMode(publicRead.isChecked, publicWrite.isChecked, publicExec.isChecked)

        permissionAbsolute.text = "${ownerAbsolute}${groupAbsolute}${publicAbsolute}"
        permissionSymbolic.text = "${ownerSymbolic}${groupSymbolic}${publicSymbolic}"
    }

    private fun getPermissionAbsoluteMode(read: Boolean, write: Boolean, execute: Boolean): Int {
        val permission: Int = 0
        if(read && write && execute) {
            return 7
        }
        if(read && write) {
            return 6
        }
        if(read && execute) {
            return 5
        }
        if(read) {
            return 4
        }
        if(write && execute) {
            return 3
        }
        if(write) {
            return 2
        }
        if(execute) {
            return 1
        }
        return permission
    }

    private fun getPermissionSymbolicMode(read: Boolean, write: Boolean, execute: Boolean): String {
        val permission: String = "---"
        if(read && write && execute) {
            return "rwx"
        }
        if(read && write) {
            return "rw-"
        }
        if(read && execute) {
            return "r-x"
        }
        if(read) {
            return "r--"
        }
        if(write && execute) {
            return "-wx"
        }
        if(write) {
            return "-w-"
        }
        if(execute) {
            return "--x"
        }
        return permission
    }
}