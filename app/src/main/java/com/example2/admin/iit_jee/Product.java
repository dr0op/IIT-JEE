package com.example2.admin.iit_jee;

/**
 * Created by Admin on 11-05-2017.
 */
//Each row in the database can be represented by an object
//Columns will represent the objects properties
public class Product {
    private String _QUOTA;
    private String _college;

    public Product(String _QUOTA, String _college, String _branch, int _OPC, int _OPO, int _SCO, int _SCC, int _STO, int _STC, int _BCO, int _BCC) {
        this._QUOTA = _QUOTA;
        this._college = _college;
        this._branch = _branch;
        this._OPC = _OPC;
        this._OPO = _OPO;
        this._SCO = _SCO;
        this._SCC = _SCC;
        this._STO = _STO;
        this._STC = _STC;
        this._BCO = _BCO;
        this._BCC = _BCC;
    }

    private String _branch;
    private int _OPO;
    private int _OPC;
    private int _SCO;
    private int _SCC;
    private int _STO;
    private int _STC;
    private int _BCO;
    private int _BCC;

    public void set_QUOTA(String _QUOTA) {
        this._QUOTA = _QUOTA;
    }

    public void set_college(String _college) {
        this._college = _college;
    }

    public void set_branch(String _branch) {
        this._branch = _branch;
    }

    public void set_OPO(int _OPO) {
        this._OPO = _OPO;
    }

    public void set_OPC(int _OPC) {
        this._OPC = _OPC;
    }

    public void set_SCO(int _SCO) {
        this._SCO = _SCO;
    }

    public void set_SCC(int _SCC) {
        this._SCC = _SCC;
    }

    public void set_STO(int _STO) {
        this._STO = _STO;
    }

    public void set_STC(int _STC) {
        this._STC = _STC;
    }

    public void set_BCO(int _BCO) {
        this._BCO = _BCO;
    }

    public void set_BCC(int _BCC) {
        this._BCC = _BCC;
    }

    public String get_QUOTA() {
        return _QUOTA;
    }

    public String get_college() {
        return _college;
    }

    public String get_branch() {
        return _branch;
    }

    public int get_OPO() {
        return _OPO;
    }

    public int get_OPC() {
        return _OPC;
    }

    public int get_SCO() {
        return _SCO;
    }

    public int get_SCC() {
        return _SCC;
    }

    public int get_STO() {
        return _STO;
    }

    public int get_STC() {
        return _STC;
    }

    public int get_BCO() {
        return _BCO;
    }

    public int get_BCC() {
        return _BCC;
    }
}
