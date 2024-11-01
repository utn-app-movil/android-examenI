package cr.ac.utn.appmovil.identities

import identities.Identifier

// Definición de la entidad Email que hereda de Identifier
class ema_Email : Identifier {

    private var _subject: String = ""
    private var _message: String = ""
    private var _sendDate: String = ""
    private var _cc: String = ""
    private var _bcc: String = ""

    constructor()

    constructor(
        id: String,
        subject: String,
        message: String,
        sendDate: String,
        cc: String,
        bcc: String
    ) {

        this._subject = subject
        this._message = message
        this._sendDate = sendDate
        this._cc = cc
        this._bcc = bcc
    }

    override val FullDescription: String
        get() = ""


    var Subject: String
        get() = _subject
        set(value) { _subject = value }

    var Message: String
        get() = _message
        set(value) { _message = value }

    var SendDate: String
        get() = _sendDate
        set(value) { _sendDate = value }

    var CC: String
        get() = _cc
        set(value) { _cc = value }

    var BCC: String
        get() = _bcc
        set(value) { _bcc = value }
}


