package org.jetbrains.kotlinconf.ui

import android.content.*
import android.os.*
import android.support.v7.app.*
import android.view.*
import org.jetbrains.anko.*
import org.jetbrains.kotlinconf.*
import org.jetbrains.kotlinconf.model.*
import org.jetbrains.kotlinconf.presentation.*

class MainActivity : AppCompatActivity(), AnkoComponent<Context>, NavigationManager, AnkoLogger {
    private val repository by lazy { (application as KotlinConfApplication).dataRepository }
    private val presenter by lazy { MainScreenPresenter(this, repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(createView(AnkoContext.create(this)))

        if (savedInstanceState == null) {
            showSessions()
            presenter.onCreate()
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        frameLayout {
            id = R.id.fragment_container
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_info -> showInfo()
            android.R.id.home -> supportFragmentManager.popBackStack()
        }
        return true
    }

    private fun showInfo() {
        if (supportFragmentManager.findFragmentByTag(InfoFragment.TAG) != null)
            return

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            .addToBackStack("Info")
            .replace(R.id.fragment_container, InfoFragment(), InfoFragment.TAG)
            .commit()
    }

    override fun showSessions() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, SessionPagerFragment(), SessionListFragment.TAG)
            .commit()
    }

    override fun showPrivacyPolicyDialog() {
        PrivacyPolicyAcceptanceFragment().show(supportFragmentManager, PrivacyPolicyAcceptanceFragment.TAG)
    }

    override fun showSessionDetails(session: Session) {
        val fragment = SessionDetailsFragment.forSession(session)
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            .addToBackStack("SessionData")
            .replace(R.id.fragment_container, fragment, SessionDetailsFragment.TAG)
            .commit()
    }

    override fun showSpeakers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showFAQ() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showVenueMap() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSpeaker(speaker: Speaker) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private const val SEARCH_QUERY_KEY = "SearchQuery"
    }
}
