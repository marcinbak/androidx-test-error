package de.neofonie.fragementtesterror

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [21], application = FragmentForTestTest.TestApp::class)
class FragmentForTestTest {

    lateinit var fragment: FragmentForTest
    lateinit var fragmentController: FragmentScenario<FragmentForTest>
    lateinit var fragmentFactory: FragmentFactory

    @Before
    fun setUp() {
        fragment = FragmentForTest()
        fragmentFactory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String, args: Bundle?): Fragment {
                return fragment
            }
        }

        fragmentController = launchFragmentInContainer(factory = fragmentFactory)
        fragmentController.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun onCreateView() {
    }

    class TestApp : Application() {
        override fun onCreate() {
            super.onCreate()
            setTheme(R.style.AppTheme)
        }
    }
}