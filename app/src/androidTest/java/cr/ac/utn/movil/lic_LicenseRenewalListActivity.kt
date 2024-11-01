package cr.ac.utn.movil

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager


class lic_LicenseRenewalListActivity() : AppCompatActivity() {
    private lateinit var binding: LicActivityLicenseRenewalListBinding

    class LicActivityLicenseRenewalListBinding {

        val recyclerView: Any
            get() {
                TODO()
            }
        val root: Any = TODO()

        companion object {
            fun <LayoutInflater> inflate(layoutInflater: LayoutInflater) {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.apply {
            var layoutManager = LinearLayoutManager(this@lic_LicenseRenewalListActivity)
            var adapter = LicenseRenewalAdapter(LicenseRenewalModel.getAllRenewals())
        }
    }

    class LicenseRenewalModel {
        companion object {
            fun getAllRenewals() {

            }
        }

    }

    class LicenseRenewalAdapter(allRenewals: Any) {

    }

    private fun setContentView(root: Any) {
        TODO("Not yet implemented")

    }
}
