package com.example2.admin.iit_jee;

/**
 * Created by Admin on 12-05-2017.
 */

public class product1 {
    private String _cid;
    private String _cname;

    public product1(String _cid, String _cname) {
        this._cid = _cid;
        this._cname = _cname;
    }

    public String get_cid() {
        return _cid;
    }

    public String get_cname() {
        return _cname;
    }

    public void set_cname(String _cname) {
        this._cname = _cname;
    }
}