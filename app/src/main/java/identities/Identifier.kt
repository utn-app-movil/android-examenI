package identities

class Identifier(
    private var _id: String = "",
    private var _fullDescription: String = ""
) {
    var id: String
        get() = this._id
        set(value) { this._id = value }

    var FullDescription: String
        get() = this._fullDescription
        set(value) { this._fullDescription = value }
}
