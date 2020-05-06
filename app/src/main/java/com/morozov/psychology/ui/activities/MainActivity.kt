package com.morozov.psychology.ui.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import android.transition.Fade
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.vending.billing.IInAppBillingService
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.psychology.DefaultApplication
import com.morozov.psychology.R
import com.morozov.psychology.mvp.presenters.MainPresenter
import com.morozov.psychology.mvp.views.MainView
import com.morozov.psychology.ui.fragments.deep.mind.fragments.*
import com.morozov.psychology.ui.fragments.deep.mind.fragments.models.ContraRealmModel
import com.morozov.psychology.ui.fragments.deep.mind.fragments.models.ThinkRealmModel
import com.morozov.psychology.ui.fragments.diary.DiaryEditorFragment
import com.morozov.psychology.ui.fragments.diary.DiaryMainFragment
import com.morozov.psychology.ui.fragments.diary.DiaryThinkViewingFragment
import com.morozov.psychology.ui.fragments.examples.*
import com.morozov.psychology.ui.fragments.mind.change.MindChangeFragment
import com.morozov.psychology.ui.fragments.mind.change.MindChangeTest
import com.morozov.psychology.ui.fragments.mind.change.MindChangeThinkTestFragment
import com.morozov.psychology.ui.fragments.mind.change.changing.black.white.MCBlackWhiteFragment
import com.morozov.psychology.ui.fragments.mind.change.changing.commitment.MCCommitment_1_Fragment
import com.morozov.psychology.ui.fragments.mind.change.changing.commitment.MCCommitment_2_Fragment
import com.morozov.psychology.ui.fragments.mind.change.changing.commitment.MCCommitment_3_Fragment
import com.morozov.psychology.ui.fragments.mind.change.changing.commitment.MCCommitment_4_Fragment
import com.morozov.psychology.ui.fragments.mind.change.changing.deprecation.MCDeprecation_1_Fragment
import com.morozov.psychology.ui.fragments.mind.change.changing.deprecation.MCDeprecation_2_Fragment
import com.morozov.psychology.ui.fragments.mind.change.changing.deprecation.MCDeprecation_3_Fragment
import com.morozov.psychology.ui.fragments.mind.change.changing.disastorous.MCDisastorous_1_Fragment
import com.morozov.psychology.ui.fragments.mind.change.changing.disastorous.MCDisastorous_2_Fragment
import com.morozov.psychology.ui.fragments.mind.change.changing.disastorous.MCDisastorous_3_Fragment
import com.morozov.psychology.ui.fragments.mind.change.changing.emotional.MCEmotionalFragment
import com.morozov.psychology.ui.fragments.mind.change.changing.labeling.MCLabelingFragment
import com.morozov.psychology.ui.fragments.mind.change.changing.mind.reading.MCMindReadingFragment
import com.morozov.psychology.ui.fragments.mind.change.changing.minimalism.MCMinimalismFragment
import com.morozov.psychology.ui.fragments.mind.change.changing.overgeneration.MCOvergenerationFragment
import com.morozov.psychology.ui.fragments.mind.change.changing.personalization.MCPersonalizationFragment
import com.morozov.psychology.ui.fragments.mind.change.think.mistake.MCThinkMistake_1_Fragment
import com.morozov.psychology.ui.fragments.mind.change.think.mistake.MCThinkMistake_2_Fragment
import com.morozov.psychology.ui.fragments.mind.change.changing.tunnel.MCTunnelFragment
import com.morozov.psychology.ui.fragments.mind.change.homework.black.white.HmBlackAndWhiteFragment
import com.morozov.psychology.ui.fragments.mind.change.homework.commitment.HmCommitment_1_Fragment
import com.morozov.psychology.ui.fragments.mind.change.homework.commitment.HmCommitment_2_Fragment
import com.morozov.psychology.ui.fragments.mind.change.homework.commitment.HmCommitment_3_Fragment
import com.morozov.psychology.ui.fragments.mind.change.homework.deprecation.HmDeprecationFragment
import com.morozov.psychology.ui.fragments.mind.change.homework.disastorous.HmDisastorous_1_Fragment
import com.morozov.psychology.ui.fragments.mind.change.homework.disastorous.HmDisastorous_2_Fragment
import com.morozov.psychology.ui.fragments.mind.change.homework.emotional.HmEmotionalFragment
import com.morozov.psychology.ui.fragments.mind.change.homework.labeling.HmLabelingFragment
import com.morozov.psychology.ui.fragments.mind.change.homework.main.HmMainFragment
import com.morozov.psychology.ui.fragments.mind.change.homework.mind.reading.HmMindReading_1_Fragment
import com.morozov.psychology.ui.fragments.mind.change.homework.mind.reading.HmMindReading_2_Fragment
import com.morozov.psychology.ui.fragments.mind.change.homework.minimalism.HmMinimalismFragment
import com.morozov.psychology.ui.fragments.mind.change.homework.overgeneration.HmOvergenerationFragment
import com.morozov.psychology.ui.fragments.mind.change.homework.personalization.HmPersonalizationFragment
import com.morozov.psychology.ui.fragments.mind.change.homework.tunnel.HmTunnelFragment
import com.morozov.psychology.ui.fragments.settings.*
import com.morozov.psychology.ui.fragments.tests.*
import com.morozov.psychology.utility.*
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.text.DateFormat
import java.util.*

class MainActivity : MvpAppCompatActivity(), MainView {

    lateinit var realm: Realm

    @InjectPresenter
    lateinit var mPresenter: MainPresenter

    companion object {
        const val MAX_CLICK_DURATION = 150
        var startClickTime: Long = 0
        var startClickX: Float = 0f
        var startClickY: Float = 0f

        // Billing
        const val PRODUCT1 = "mychange_inapp"
        const val SHARED_BILLING1 = "BILLING_INAPP"
        const val PURCHASED_STATUS = 0
        var openPurchase = false
        const val CODE_PURCHASE = 1234
        var purchases = listOf<InAppProduct>()
    }

    private val billingResult = MutableLiveData<Boolean>()

    inner class InAppProduct {

        var sku: String? = null
            internal set
        internal var storeName: String? = null
        internal var storeDescription: String? = null
        internal var price: String? = null
        internal var isSubscription: Boolean = false
        internal var priceAmountMicros: Int = 0
        internal var currencyIsoCode: String? = null

        val type: String
            get() = if (isSubscription) "subs" else "inapp"

    }



    /*
    * Bottom Navigation
    *
    * */
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_examples -> {
                val exCardsFragment = ExCardsFragment()
                exCardsFragment.mActivityPresenter = mPresenter

                clearBackStack()
                setFragment(exCardsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_diary -> {
                val diaryFragment = DiaryMainFragment()
                diaryFragment.mActivityPresenter = mPresenter

                clearBackStack()
                setFragment(diaryFragment)

                if (!MySharedPreferences.getBoolPreference(applicationContext, AppConstants.PREF_DIARY)) {
                    val customDialog = CustomDialog()
                    customDialog.setTitle(getString(R.string.dialog_diary_title))
                    customDialog.setDescription(getString(R.string.dialog_diary_description))
                    customDialog.setOk(getString(R.string.dialog_diary_ok))
                    customDialog.show(supportFragmentManager, CustomDialog::class.simpleName)

                    MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_DIARY, true)
                }

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tests -> {
                val testsFragment = TestsFragment()

                testsFragment.mActivityPresenter = mPresenter

                clearBackStack()
                setFragment(testsFragment)

                if (!MySharedPreferences.getBoolPreference(applicationContext, AppConstants.PREF_DIALOG_TESTS)) {
                    val customDialog = CustomDialog()
                    customDialog.setTitle(getString(R.string.dialog_tests_title))
                    customDialog.setDescription(getString(R.string.dialog_tests_description))
                    customDialog.show(supportFragmentManager, CustomDialog::class.simpleName)

                    MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_DIALOG_TESTS, true)
                }

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mind_change -> {
                val mindChangeFragment = MindChangeFragment()
                mindChangeFragment.mActivityPresenter = mPresenter

                clearBackStack()
                setFragment(mindChangeFragment)

//                setFragment(SectionInDevelopFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                val settingsConsultFragment = SettingsConsultFragment()
                settingsConsultFragment.mActivityPresenter = mPresenter

                clearBackStack()
                setFragment(settingsConsultFragment)

//                setFragment(SectionInDevelopFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    internal var inAppBillingService: IInAppBillingService? = null

    private var serviceConnection: ServiceConnection? = object : ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
            inAppBillingService = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            inAppBillingService = IInAppBillingService.Stub.asInterface(service)
            DefaultApplication.inAppService = inAppBillingService!!
            try{
                readMyPurchases()
                purchases = getInAppPurchases (PRODUCT1)
            }catch (e: Exception){

            }

        }

    }

    @Throws(Exception::class)
    private fun getInAppPurchases(vararg productIds: String): List<InAppProduct> {
        val skuList = ArrayList(Arrays.asList(*productIds))
        val query = Bundle()
        query.putStringArrayList("ITEM_ID_LIST", skuList)
        val skuDetails = inAppBillingService!!.getSkuDetails(
            3, packageName, "inapp", query)
        val responseList = skuDetails.getStringArrayList("DETAILS_LIST")
        val result = ArrayList<InAppProduct>()
        for (responseItem in responseList!!) {
            val jsonObject = JSONObject(responseItem)
            val product = InAppProduct()
            product.sku = jsonObject.getString("productId")
            product.storeName = jsonObject.getString("title")
            product.storeDescription = jsonObject.getString("description")
            product.price = jsonObject.getString("price")
            product.isSubscription = jsonObject.getString("type") == "subs"
            product.priceAmountMicros = Integer.parseInt(jsonObject.getString("price_amount_micros"))
            product.currencyIsoCode = jsonObject.getString("price_currency_code")
            result.add(product)
        }
        return result
    }




    private fun readMyPurchases(){
        val result = inAppBillingService!!.getPurchases(
            3, packageName, "inapp", null)

        if (result.getInt("RESPONSE_CODE", -1) != 0) {
            throw Exception("Invalid response code")
        }
        val responseList = result.getStringArrayList("INAPP_PURCHASE_DATA_LIST")

        for (purchaseData in responseList!!) {
            val jsonObject = JSONObject(purchaseData)
            val purchaseState = jsonObject.getInt("purchaseState")
            if (purchaseState == PURCHASED_STATUS) {
                when (jsonObject.getString("productId")) {
                    PRODUCT1 -> {
                        openPurchase = true
                        getSharedPreferences(SHARED_BILLING1, Context.MODE_PRIVATE).edit()
                            .putBoolean(SHARED_BILLING1, true).apply()
                    }
                }
                refreshAfterBuying()
            }
        }
    }

    public fun refreshAfterBuying() {
        billingResult.value = true
    }

    public fun buy(): LiveData<Boolean> {
        purchase(0)
        return billingResult
    }

    private fun purchase(number: Int) {
        try {
            purchaseProduct(purchases[number])
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Throws(Exception::class)
    private fun purchaseProduct(product: InAppProduct) {
        val sku = product.sku
        val type = product.type
        val buyIntentBundle = inAppBillingService!!.getBuyIntent(
            3, packageName,
            sku, type, null)
        val pendingIntent = buyIntentBundle.getParcelable<PendingIntent>("BUY_INTENT")
        startIntentSenderForResult(pendingIntent!!.intentSender,
            CODE_PURCHASE, Intent(), 0, 0,
            0, null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_PURCHASE) {
            val responseCode = data!!.getIntExtra("RESPONSE_CODE", -1)
            if (responseCode == PURCHASED_STATUS) {
                val purchaseData = data.getStringExtra("INAPP_PURCHASE_DATA")
                readPurchase(purchaseData)
            }
        }
    }

    private fun readPurchase(purchaseData: String?) {
        try {
            val jsonObject = JSONObject(purchaseData)
            when (jsonObject.getString("productId")) {
                PRODUCT1 -> {
                    openPurchase = true
                    getSharedPreferences(SHARED_BILLING1, Context.MODE_PRIVATE).edit()
                        .putBoolean(SHARED_BILLING1, true).apply()
                }

            }
            refreshAfterBuying()
        }catch (e: Exception){}

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realm = Realm.getDefaultInstance()
        setCustomTheme()
        setContentView(R.layout.activity_main)
        initPreferences()
        initPurchaseService()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        linearBack.setOnClickListener {
            onBackPressed()
        }

        if (!MySharedPreferences.getBoolPreference(applicationContext, AppConstants.PREF_DIALOG_FIRST_HELLO)) {
            MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_DIALOG_FIRST_HELLO, true)
            showHelloDialog()
        } else {
            startCustomActivity()
        }
    }

    private fun initPreferences() {
        openPurchase = getSharedPreferences(SHARED_BILLING1, Context.MODE_PRIVATE).getBoolean(
            SHARED_BILLING1, false)
    }

    private fun initPurchaseService() {
        val serviceIntent = Intent("com.android.vending.billing.InAppBillingService.BIND")
        serviceIntent.setPackage("com.android.vending")
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onBackPressed() {
        hideKeyboard(this)
        when (supportFragmentManager.backStackEntryCount) {
            0 -> super.onBackPressed()
            1 -> {
                val fragment = supportFragmentManager.fragments[supportFragmentManager.fragments.size - 1]
                when (fragment) {
                    is DeepEditContraFragment -> {
                        CustomYesNoDialog.showDialog("Вы действительно хотите выйти? Введённые данные не будут сохранены",
                            "Да", "Отмена",
                            Runnable {
                                showBottomNav()
                                hideBackArrow()
                                supportFragmentManager.popBackStack()
                            },
                            Runnable { }, supportFragmentManager)
                    }
                    is DiaryEditorFragment -> {
                        CustomYesNoDialog.showDialog("Вы действительно хотите выйти из заполнения ситуации?",
                            "Да", "Отмена",
                            Runnable {
                                showBottomNav()
                                hideBackArrow()
                                supportFragmentManager.popBackStack()
                            },
                            Runnable { }, supportFragmentManager)
                    }
                    is TestsAboutFragment -> {
                        CustomYesNoDialog.showDialog("Вы действительно хотите выйти из теста \"Немного о вас\"?",
                            "Да", "Отмена",
                            Runnable {
                                showBottomNav()
                                hideBackArrow()
                                supportFragmentManager.popBackStack()
                            },
                            Runnable { }, supportFragmentManager)
                    }
                    else -> {
                        showBottomNav()
                        hideBackArrow()
                        supportFragmentManager.popBackStack()
                    }
                }
            }
            else -> {
                val fragment = supportFragmentManager.fragments[supportFragmentManager.fragments.size - 1]
                when (fragment) {
                    is DeepEditContraFragment -> {
                        CustomYesNoDialog.showDialog("Вы действительно хотите выйти? Введённые данные не будут сохранены",
                            "Да", "Отмена",
                            Runnable {
                                showBottomNav()
                                hideBackArrow()
                                supportFragmentManager.popBackStack()
                            },
                            Runnable { }, supportFragmentManager)
                    }
                    is ExTestsFragment, is ExFixTestsFragment -> {
                        CustomYesNoDialog.showDialog("Вы действительно хотите закончить эксперимент?",
                        "Да", "Отмена",
                        Runnable {
                            hideBackArrow()
                            supportFragmentManager.popBackStack()
                        },
                        Runnable { }, supportFragmentManager)
                    }
                    is DiaryEditorFragment -> {
                        CustomYesNoDialog.showDialog("Вы действительно хотите выйти из заполнения ситуации?",
                            "Да", "Отмена",
                            Runnable {
                                supportFragmentManager.popBackStack()
                            },
                            Runnable { }, supportFragmentManager)
                    }
                    is TestsQuizFragment -> {
                        CustomYesNoDialog.showDialog("Вы действительно хотите покинуть тест?",
                            "Да", "Отмена",
                            Runnable {
                                supportFragmentManager.popBackStack()
                            },
                            Runnable { }, supportFragmentManager)
                    }
                    is MindChangeTest -> {
                        CustomYesNoDialog.showDialog("Вы действительно хотите уйти с этой страницы?",
                            "Да", "Отмена",
                            Runnable {
                                supportFragmentManager.popBackStack()
                            },
                            Runnable { }, supportFragmentManager)
                    }
                    is SettingsWallpaperFragment -> {
                        showBackArrow()
                        supportFragmentManager.popBackStack()
                    }
                    else -> supportFragmentManager.popBackStack()
                }
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev != null) {
            when {
                ev.action == MotionEvent.ACTION_DOWN -> {
                    startClickTime = Calendar.getInstance().timeInMillis
                    startClickX = ev.rawX
                    startClickY = ev.rawY
                }
                ev.action == MotionEvent.ACTION_UP -> {
                    val clickDuration = Calendar.getInstance().timeInMillis - startClickTime

                    if (clickDuration < MAX_CLICK_DURATION && startClickX == ev.rawX && startClickY == ev.rawY) {
                        val v = currentFocus
                        if (v is EditText) {
                            val outRect = Rect()
                            v.getGlobalVisibleRect(outRect)
                            if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                                v.clearFocus()
                                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                imm.hideSoftInputFromWindow(v.windowToken, 0)
                            }
                        }
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    /*
    * Interface controls
    * (MainView impl)
    * */
    override fun refreshActivity() {
        finish()
        startActivity(intent)
    }

    override fun setCustomTheme() {
        val preference = MySharedPreferences.getStrPreference(applicationContext, AppConstants.PREF_PROG_STYLE)

        when (preference) {
            AppConstants.EMPTY_PREF -> setTheme(R.style.AppTheme)
            AppConstants.PREF_COLOR_DEFAULT -> setTheme(R.style.AppTheme)
            AppConstants.PREF_COLOR_GREEN -> setTheme(R.style.GreenAppTheme)
            AppConstants.PREF_COLOR_TURQUOISE -> setTheme(R.style.TurquoiseAppTheme)
            AppConstants.PREF_COLOR_BLUE -> setTheme(R.style.BlueAppTheme)
            AppConstants.PREF_COLOR_YELLOW -> setTheme(R.style.YellowAppTheme)
            AppConstants.PREF_COLOR_ORANGE -> setTheme(R.style.OrangeAppTheme)
            AppConstants.PREF_COLOR_RED -> setTheme(R.style.RedAppTheme)
            else -> setTheme(R.style.AppTheme)
        }
    }

    override fun showHelloDialog() {
        hideBottomNav()
        hideBackArrow()
        imageMainBack.setImageDrawable(getDrawable(R.drawable.wallpaper_3))
        imageMainBack.alpha = 1f

        val customDialog = CustomDialog()
        customDialog.setTitle(getString(R.string.dialog_first_hello_title))
        customDialog.setDescription(getString(R.string.dialog_first_hello_description))
        customDialog.setOk(getString(R.string.dialog_first_hello_ok), Runnable {
            showBottomNav()
            hideBackArrow()
            imageMainBack.alpha = 0.5f
            startCustomActivity()
        })
        customDialog.show(supportFragmentManager, CustomDialog::class.simpleName)
    }

    override fun startCustomActivity() {
        when (MySharedPreferences.getStrPreference(applicationContext, AppConstants.PREF_WALLPAPER)) {
            AppConstants.EMPTY_PREF -> imageMainBack.setImageDrawable(ColorDrawable(resources.getColor(R.color.white)))
            AppConstants.PREF_WALLP_DEF -> imageMainBack.setImageDrawable(ColorDrawable(resources.getColor(R.color.white)))
            AppConstants.PREF_WALLP_1 -> imageMainBack.setImageDrawable(getDrawable(R.drawable.wallpaper_1))
            AppConstants.PREF_WALLP_2 -> imageMainBack.setImageDrawable(getDrawable(R.drawable.wallpaper_2))
            AppConstants.PREF_WALLP_3 -> imageMainBack.setImageDrawable(getDrawable(R.drawable.wallpaper_3))
            else -> imageMainBack.setImageDrawable(ColorDrawable(resources.getColor(R.color.white)))
        }

        mPresenter.showExCards()
    }

    override fun showBottomNav() {
        navigation.visibility = View.VISIBLE
    }

    override fun hideBottomNav() {
        navigation.visibility = View.GONE
    }

    override fun showBackArrow() {
        linearBack.visibility = View.VISIBLE
    }

    override fun hideBackArrow() {
        linearBack.visibility = View.GONE
    }

    override fun setBackground(drawable: Drawable) {
        imageMainBack.setImageDrawable(drawable)
    }

    /**
     * Deep mind
     *
     * */
    override fun showDeepMindTest() {
        val fragment = DeepMindTestFragment()
        fragment.mActivityPresenter = mPresenter
        hideKeyboard(this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentMain, fragment)
            .addToBackStack(DeepMindTestFragment::class.java.simpleName)
            .commit()
    }

    override fun showDeepMindTestShort() {
        val fragment = DeepMindTestShortFragment()
        fragment.mActivityPresenter = mPresenter
        hideKeyboard(this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentMain, fragment)
            .addToBackStack(DeepMindTestShortFragment::class.java.simpleName)
            .commit()
    }

    override fun showSelectMind(thinks: List<String>) {
        val fragment = DeepSelectFragment()
        fragment.mActivityPresenter = mPresenter
        fragment.thinkList = thinks
        hideKeyboard(this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.contentMain, fragment)
            .addToBackStack(DeepSelectFragment::class.java.simpleName)
            .commit()
    }

    override fun showMakeContras(think: String, showSelectAnother: Boolean) {
        val fragment = DeepMakeContrasFragment()
        fragment.mActivityPresenter = mPresenter
        fragment.mThink = think
        fragment.showSelectAnother = showSelectAnother
        hideKeyboard(this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.contentMain, fragment)
            .addToBackStack(DeepMakeContrasFragment::class.java.simpleName)
            .commit()
    }

    override fun showEditContra(think: ThinkRealmModel, contra: ContraRealmModel) {
        val fragment = DeepEditContraFragment()
        fragment.mActivityPresenter = mPresenter
        fragment.mThinkModel = think
        fragment.mContraModel = contra
        hideKeyboard(this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.contentMain, fragment)
            .addToBackStack(DeepEditContraFragment::class.java.simpleName)
            .commit()
    }

    override fun showSelectThinkList() {
        val fragment = DeepSelectThinkFragment()
        fragment.mActivityPresenter = mPresenter
        hideKeyboard(this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.contentMain, fragment)
            .addToBackStack(DeepSelectThinkFragment::class.java.simpleName)
            .commit()
    }

    /*
                    * Experiments section controls
                    * (MainView impl)
                    * */
    override fun showAboutApplication() {
        val fragment = AboutApplicationFragment()
        hideKeyboard(this)

        supportFragmentManager.beginTransaction()
            .replace(R.id.contentMain, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun showExCards() {
        hideKeyboard(this)
        navigation.selectedItemId = R.id.navigation_examples
    }

    override fun showExDescr(image: ImageView?, position: Int) {
        val exDescriptionFragment = ExDescriptionFragment()
        hideKeyboard(this)

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)

        exDescriptionFragment.arguments = bundle
        exDescriptionFragment.mActivityPresenter = mPresenter

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            exDescriptionFragment.sharedElementEnterTransition = DetailsTransition()
            exDescriptionFragment.enterTransition = Fade()
            exDescriptionFragment.sharedElementReturnTransition = DetailsTransition()
        }

        val transaction = supportFragmentManager.beginTransaction()

        if (image != null)
            transaction.addSharedElement(image, "experimentImage")

        transaction.replace(R.id.contentMain, exDescriptionFragment)
                   .addToBackStack(null)

        transaction.commit()
    }

    override fun showExFixDescr(image: ImageView?, position: Int) {
        val exFixDescriptionFragment = ExFixDescriptionFragment()

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)
        hideKeyboard(this)

        exFixDescriptionFragment.arguments = bundle
        exFixDescriptionFragment.mActivityPresenter = mPresenter

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            exFixDescriptionFragment.sharedElementEnterTransition = DetailsTransition()
            exFixDescriptionFragment.enterTransition = Fade()
            exFixDescriptionFragment.sharedElementReturnTransition = DetailsTransition()
        }

        val transaction = supportFragmentManager.beginTransaction()

        if (image != null)
            transaction.addSharedElement(image, "fixingImage")

        transaction.replace(R.id.contentMain, exFixDescriptionFragment)
            .addToBackStack(null)

        transaction.commit()
    }

    override fun showExTest(position: Int) {
        val exTestsFragment = ExTestsFragment()

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)
        hideKeyboard(this)

        exTestsFragment.arguments = bundle
        exTestsFragment.mActivityPresenter = mPresenter

        setFragment(exTestsFragment, true)
    }

    override fun showExFixTest(position: Int) {
        val exFixTestsFragment = ExFixTestsFragment()

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)
        hideKeyboard(this)

        exFixTestsFragment.arguments = bundle
        exFixTestsFragment.mActivityPresenter = mPresenter

        setFragment(exFixTestsFragment, true)
    }

    override fun showExResults(position: Int) {
        val exResultsFragment = ExResultsFragment()

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)

        exResultsFragment.arguments = bundle
        exResultsFragment.mActivityPresenter = mPresenter
        hideKeyboard(this)

        clearBackStackForResults()
        setFragment(exResultsFragment, true)
    }

    override fun showExFixResults(position: Int) {
        val exfixResultsFragment = ExFixResultsFragment()
        hideKeyboard(this)

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)

        exfixResultsFragment.arguments = bundle
        exfixResultsFragment.mActivityPresenter = mPresenter

        clearBackStackForResults()
        setFragment(exfixResultsFragment, true)
    }

    /*
    * Diary section controls
    * (MainView impl)
    * */
    override fun showDiaryCards() {
        hideKeyboard(this)
        navigation.selectedItemId = R.id.navigation_diary
    }

    override fun showDiaryViewing(date: Date) {
        val diaryThinkViewingFragment = DiaryThinkViewingFragment()

        val bundle = Bundle()
        bundle.putSerializable(AppConstants.DIARY_SELECTED_DAY, date)
        hideKeyboard(this)

        diaryThinkViewingFragment.arguments = bundle
        diaryThinkViewingFragment.mActivityPresenter = mPresenter

        setFragment(diaryThinkViewingFragment, true)
    }

    override fun showDiaryEditor(isNew: Boolean, date: Date, showButtons: Boolean?) {
        val diaryEditorFragment = DiaryEditorFragment()

        val bundle = Bundle()
        bundle.putBoolean(AppConstants.DIARY_IS_NEW_ITEM, isNew)
        bundle.putSerializable(AppConstants.DIARY_SELECTED_DAY, date)
        when (showButtons) {
            true -> bundle.putBoolean(AppConstants.DIARY_THINK_EDITOR_SHOW_BUTTONS, true)
            false -> bundle.putBoolean(AppConstants.DIARY_THINK_EDITOR_SHOW_BUTTONS, false)
            null -> bundle.putBoolean(AppConstants.DIARY_THINK_EDITOR_SHOW_BUTTONS, false)
        }
        hideKeyboard(this)

        diaryEditorFragment.arguments = bundle
        diaryEditorFragment.mActivityPresenter = mPresenter

        setFragment(diaryEditorFragment, true)
    }

    /*
    * Test section controls
    *
    * */
    override fun showTestSection() {
        hideKeyboard(this)
        navigation.selectedItemId = R.id.navigation_tests
    }

    override fun showTestDescr(testName: String) {
        val testsDescriptionFragment = TestsDescriptionFragment()
        hideKeyboard(this)

        val bundle = Bundle()
        bundle.putString(AppConstants.TEST_NAME, testName)

        testsDescriptionFragment.arguments = bundle
        testsDescriptionFragment.mActivityPresenter = mPresenter

        setFragment(testsDescriptionFragment, true)
    }

    override fun showTestQuiz(testName: String) {
        val testsQuizFragment = TestsQuizFragment()

        val bundle = Bundle()
        bundle.putString(AppConstants.TEST_NAME, testName)

        testsQuizFragment.arguments = bundle
        testsQuizFragment.mActivityPresenter = mPresenter

        setFragment(testsQuizFragment, true)
    }

    override fun showTestQuizResults(testName: String) {
        if (!MySharedPreferences.getBoolPreference(applicationContext, AppConstants.PREF_QUIZ_MONTH)) {
            MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_QUIZ_MONTH, true)

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.add(Calendar.MONTH, 1)
            setNotification(calendar, applicationContext)
        }
        hideKeyboard(this)

        val testsResultsFragment = TestsResultsFragment()

        val bundle = Bundle()
        bundle.putString(AppConstants.TEST_NAME, testName)

        testsResultsFragment.arguments = bundle
        testsResultsFragment.mActivityPresenter = mPresenter

        clearBackStackForResults()
        setFragment(testsResultsFragment, true)
    }

    override fun showTestAbout() {
        val testsAboutFragment = TestsAboutFragment()
        hideKeyboard(this)

        testsAboutFragment.mActivityPresenter = mPresenter

        setFragment(testsAboutFragment, true)
    }

    override fun showTestAllResults() {
        val testsAllResultsFragment = TestsAllResultsFragment()
        hideKeyboard(this)

        testsAllResultsFragment.mActivityPresenter = mPresenter

        setFragment(testsAllResultsFragment, true)

        if (!MySharedPreferences.getBoolPreference(applicationContext, AppConstants.PREF_DIALOG_ALL_RESULTS)) {
            val customDialog = CustomDialog()
            customDialog.setTitle(getString(R.string.dialog_tests_all_results_title))
            customDialog.setDescription(getString(R.string.dialog_tests_all_results_description))
            customDialog.show(supportFragmentManager, CustomDialog::class.simpleName)

            MySharedPreferences.setPreference(applicationContext, AppConstants.PREF_DIALOG_ALL_RESULTS, true)
        }
    }

    override fun showTestAllResultsCards(testName: String) {
        val testsAllResultsCardsFragment = TestsAllResultsCardsFragment()
        hideKeyboard(this)

        val bundle = Bundle()
        bundle.putString(AppConstants.TEST_NAME, testName)

        testsAllResultsCardsFragment.arguments = bundle
        testsAllResultsCardsFragment.mActivityPresenter = mPresenter

        setFragment(testsAllResultsCardsFragment, true)
    }

    /*
    * Mind change section controls
    *
    * */
    override fun showMindChangeSection() {
        hideKeyboard(this)
        navigation.selectedItemId = R.id.navigation_mind_change
    }

    override fun showMindChangeThinkTest(date: Date) {
        val mindChangeThinkTestFragment = MindChangeThinkTestFragment()
        hideKeyboard(this)

        val bundle = Bundle()
        bundle.putSerializable(AppConstants.DIARY_SELECTED_DAY, date)

        mindChangeThinkTestFragment.arguments = bundle
        mindChangeThinkTestFragment.mActivityPresenter = mPresenter

        setFragment(mindChangeThinkTestFragment, true)
    }

    override fun showMCThinkMistake_1(date: Date) {
        val mcThinkMistake_1_Fragment = MCThinkMistake_1_Fragment()
        hideKeyboard(this)

        val bundle = Bundle()
        bundle.putSerializable(AppConstants.DIARY_SELECTED_DAY, date)

        mcThinkMistake_1_Fragment.arguments = bundle
        mcThinkMistake_1_Fragment.mActivityPresenter = mPresenter

        setFragment(mcThinkMistake_1_Fragment, true)
    }

    override fun showMCThinkMistake_2(date: Date) {
        val mcThinkMistake_2_Fragment = MCThinkMistake_2_Fragment()
        hideKeyboard(this)

        val bundle = Bundle()
        bundle.putSerializable(AppConstants.DIARY_SELECTED_DAY, date)

        mcThinkMistake_2_Fragment.arguments = bundle
        mcThinkMistake_2_Fragment.mActivityPresenter = mPresenter

        setFragment(mcThinkMistake_2_Fragment, true)
    }

    override fun showMCDisastorous_1() {
        val fragment = MCDisastorous_1_Fragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCDisastorous_2() {
        val fragment = MCDisastorous_2_Fragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCDisastorous_3() {
        val fragment = MCDisastorous_3_Fragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCDeprecation_1() {
        val fragment = MCDeprecation_1_Fragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCDeprecation_2() {
        val fragment = MCDeprecation_2_Fragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCDeprecation_3() {
        val fragment = MCDeprecation_3_Fragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCBlackWhite() {
        val fragment = MCBlackWhiteFragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCEmotional() {
        val fragment = MCEmotionalFragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCMindReading() {
        val fragment = MCMindReadingFragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCOvergeneration() {
        val fragment = MCOvergenerationFragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCMinimalism() {
        val fragment = MCMinimalismFragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCLabeling() {
        val fragment = MCLabelingFragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCCommitment_1() {
        val fragment = MCCommitment_1_Fragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCCommitment_2() {
        val fragment = MCCommitment_2_Fragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCCommitment_3() {
        val fragment = MCCommitment_3_Fragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCCommitment_4() {
        val fragment = MCCommitment_4_Fragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCPersonalization() {
        val fragment = MCPersonalizationFragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showMCTunnel() {
        val fragment = MCTunnelFragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }
                                    // Homework
    override fun showHmMain(date: Date) {
        val mindChangeThinkTestFragment = HmMainFragment()
                                        hideKeyboard(this)

        val bundle = Bundle()
        bundle.putSerializable(AppConstants.DIARY_SELECTED_DAY, date)

        mindChangeThinkTestFragment.arguments = bundle
        mindChangeThinkTestFragment.mActivityPresenter = mPresenter

        setFragment(mindChangeThinkTestFragment, true)
    }

    override fun showHmDisastorous_1() {
        val fragment = HmDisastorous_2_Fragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showHmDisastorous_2() {
        val fragment = HmDisastorous_1_Fragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showHmDeprecation() {
        val fragment = HmDeprecationFragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showHmBlackWhite() {
        val fragment = HmBlackAndWhiteFragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showHmEmotional() {
        val fragment = HmEmotionalFragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showHmMindReading_1() {
        val fragment = HmMindReading_1_Fragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showHmMindReading_2() {
        val fragment = HmMindReading_2_Fragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showHmOvergeneration() {
        val fragment = HmOvergenerationFragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showHmMinimalism() {
        val fragment = HmMinimalismFragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showHmLabeling() {
        val fragment = HmLabelingFragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showHmCommitment_1() {
        val fragment = HmCommitment_1_Fragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showHmCommitment_2() {
        val fragment = HmCommitment_2_Fragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showHmCommitment_3() {
        val fragment = HmCommitment_3_Fragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showHmPersonalization() {
        val fragment = HmPersonalizationFragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    override fun showHmTunnel() {
        val fragment = HmTunnelFragment()
        hideKeyboard(this)

        fragment.mActivityPresenter = mPresenter

        setFragment(fragment, true)
    }

    /*
    * Settings section controls
    *
    * */
    override fun showSettingsSection() {
        hideKeyboard(this)

        val settingsFragment = SettingsFragment()
        settingsFragment.mActivityPresenter = mPresenter
        setFragment(settingsFragment, true)
    }

    override fun showSettingsStylesSection() {
        hideKeyboard(this)

        val settingsStyleFragment = SettingsStyleFragment()
        settingsStyleFragment.mActivityPresenter = mPresenter
        setFragment(settingsStyleFragment, true)
    }

    override fun showSettingsWallpaper() {
        hideKeyboard(this)

        val settingsWallpaperFragment = SettingsWallpaperFragment()
        settingsWallpaperFragment.mActivityPresenter = mPresenter
        setFragment(settingsWallpaperFragment, true)
    }

    override fun showSettingsConsult() {
        hideKeyboard(this)

        navigation.selectedItemId = R.id.navigation_settings
    }

    /*
    * Another
    *
    * */
    override fun makeBackBlack() {
        blackBack?.visibility = View.VISIBLE
    }

    override fun makeBackWhite() {
        blackBack?.visibility = View.INVISIBLE
    }

    /*
        *  Helper methods
        *
        *  */
    private fun setNotification(time: Calendar) {
        val notificationIntent = Intent(applicationContext, ShowQuizNotification::class.java)
        val contentIntent = PendingIntent.getService(
            applicationContext, 0, notificationIntent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        am.cancel(contentIntent)
        am.setExact(AlarmManager.RTC_WAKEUP, time.timeInMillis , contentIntent)

        Log.i("MainTag", "Notification created, info: ${DateFormat.getInstance().format(time.time)}")
    }

    private fun setNotification(time: Calendar, context: Context) {
        val notificationIntent = Intent(context, NotificationBroadcastReceiver::class.java)
        val contentIntent = PendingIntent.getBroadcast(
            context, 0, notificationIntent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        am.cancel(contentIntent)

        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time.timeInMillis , contentIntent)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                val alarmClockInfo = AlarmManager.AlarmClockInfo(time.timeInMillis, contentIntent)
                am.setAlarmClock(alarmClockInfo, contentIntent)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ->{
                am.setExact(AlarmManager.RTC_WAKEUP, time.timeInMillis , contentIntent)
            }
            else -> {
                am.set(AlarmManager.RTC_WAKEUP, time.timeInMillis , contentIntent)
            }
        }

        Log.i(NotificationBroadcastReceiver.TAG, "Notification created, info: ${DateFormat.getInstance().format(time.time)}")
    }

    private fun setFragment(fragment: Fragment, b: Boolean = false) {
        contentMain.clearFocus()

        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.contentMain, fragment)

        if (b)
            transaction.addToBackStack(null)

        transaction.commit()
    }

    private fun clearBackStack() {
        var i = 0
        while (i < supportFragmentManager.backStackEntryCount){
            i++
            supportFragmentManager.popBackStack()
        }
    }

    private fun clearBackStackForResults() {
        supportFragmentManager.popBackStack()
        supportFragmentManager.popBackStack()
    }
}
