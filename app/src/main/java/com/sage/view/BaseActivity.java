package com.sage.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sage.enums.CurrentScreen;
import com.sage.utils.Utils;
import com.sage.view.fragment.dashboard.ChooseInterestFragment;
import com.sage.view.fragment.dashboard.DashboardFragment;
import com.sage.view.fragment.login.LoginFragment;
import com.sage.view.fragment.login.SignupFragment;
import com.sage.view.fragment.profile.EditProfileFragment;
import com.sage.view.fragment.profile.PostDetailFragment;
import com.sage.view.fragment.profile.ProfileFragment;
import com.sage.view.fragment.profile.ProfilePicFragment;
import com.sage.view.fragment.profile.UserProfileFragment;

/**
 * Created by Anuj Sharma on 3/30/2017.
 */

public class BaseActivity extends AppCompatActivity {
    public void navigateTo(int container, Fragment fragment, boolean isBackStack) {
        try {
            FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
            fts.replace(container, fragment, fragment.getClass().getSimpleName());
            if (isBackStack)
                fts.addToBackStack(fragment.getClass().getSimpleName());
            fts.commit();

        } catch (Exception e) {
            Utils.e(e);
        }

    }

    public void navigateToWithBundle(int container,Fragment fragment, boolean isBackStack, Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        fts.replace(container, fragment, fragment.getClass().getSimpleName());
        if(isBackStack)
            fts.addToBackStack(fragment.getClass().getSimpleName());
        fts.commit();
    }

    public void navigateReplacingCurrentWithBundle(int container,Fragment currentFragment, Fragment fragmentToNavigate, Bundle bundle) {
        fragmentToNavigate.setArguments(bundle);
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        getSupportFragmentManager().popBackStack();
        fts.replace(container, fragmentToNavigate);
        fts.addToBackStack(fragmentToNavigate.getClass().getSimpleName());
        fts.remove(currentFragment).commit();
    }

    public void addFragment(int container,Fragment fragment, boolean isBackStack) {
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        fts.add(container, fragment, fragment.getClass().getSimpleName());
        if(isBackStack)
            fts.addToBackStack(fragment.getClass().getSimpleName());
        fts.commit();
    }

    public void addFragmentWithBundle(int container,Fragment fragment, boolean isBackStack, Bundle bundle) {
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        fragment.setArguments(bundle);
        fts.add(container, fragment, fragment.getClass().getSimpleName());
        if(isBackStack)
            fts.addToBackStack(fragment.getClass().getSimpleName());
        fts.commit();
    }

    public void oneStepBack() {
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() >= 1) {
            fragmentManager.popBackStackImmediate();
            fts.commit();
        } else {
            supportFinishAfterTransition();
        }
    }

    /**
     * Generic method to change fragment
     */
    public void changeScreen(int container, CurrentScreen currentScreen, boolean isAddFragment, boolean isBackStack, Bundle bundle) {
        Fragment currentFragment = null;
        switch (currentScreen) {
            case LOGIN_SCREEN:
                currentFragment = new LoginFragment();
                break;
            case FORGOT_PWD_SCREEN:
//                currentFragment = new ForgotPwdFragment();
                break;
            case SIGNUP_SCREEN:
                currentFragment = new SignupFragment();
                break;
            case CHOOSE_INTEREST_SCREEN:
                currentFragment = new ChooseInterestFragment();
                break;
            case DASHBOARD_SCREEEN:
                currentFragment = new DashboardFragment();
                break;
            case PROFILE_SCREEN:
                currentFragment = new ProfileFragment();
                break;
            case EDIT_PROFILE_SCREEN:
                currentFragment = new EditProfileFragment();
                break;
            case PROFILE_PIC_SCREEN:
                currentFragment = new ProfilePicFragment();
                break;
            case POST_DETAIL_SCREEN:
                currentFragment = new PostDetailFragment();
                currentFragment.setArguments(bundle);
                break;
            case USER_PROFILE_SCREEN:
                currentFragment = new UserProfileFragment();
                break;
        }
        if (currentFragment == null) {
            return;
        }
        if (isAddFragment) {
            //Add Fragment
            if (isBackStack)
                addFragmentWithBundle(container, currentFragment, true, bundle);
            else
                addFragmentWithBundle(container, currentFragment, false, bundle);
        } else {
            //Replace Fragment
            if (isBackStack)
                navigateToWithBundle(container, currentFragment, true, bundle);
            else
                navigateToWithBundle(container, currentFragment, false, bundle);
        }


    }
}
