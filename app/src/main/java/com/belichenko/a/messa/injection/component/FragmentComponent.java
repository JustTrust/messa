package com.belichenko.a.messa.injection.component;


import com.belichenko.a.messa.injection.PerFragment;
import com.belichenko.a.messa.injection.module.FragmentModule;
import com.belichenko.a.messa.ui.fragments.ChooseLoginMethodFragment;
import com.belichenko.a.messa.ui.fragments.EmailLoginFragment;
import com.belichenko.a.messa.ui.fragments.chat.MessageListFragment;
import com.belichenko.a.messa.ui.fragments.chat.UserListFragment;

import dagger.Component;


@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(ChooseLoginMethodFragment chooseLoginMethodFragmentFragment);

    void inject(EmailLoginFragment emailLoginFragment);

    void inject(UserListFragment userListFragment);

    void inject(MessageListFragment messageListFragment);

}
