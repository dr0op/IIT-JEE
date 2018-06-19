package com.example2.admin.iit_jee;

/**
 * Created by Admin on 12-05-2017.
 */

public class product2 {
    private String _bid;
    private String _bname;

    public product2(String _bid, String _bname) {
        this._bid = _bid;
        this._bname = _bname;
    }

    public String get_bid() {
        return _bid;
    }

    public String get_bname() {
        return _bname;
    }

    public void set_bid(String _bid) {
        this._bid = _bid;
    }

    public void set_bname(String _bname) {
        this._bname = _bname;
    }
}
