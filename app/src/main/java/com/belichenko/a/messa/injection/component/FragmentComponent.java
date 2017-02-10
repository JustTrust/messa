package com.belichenko.a.messa.injection.component;


import com.belichenko.a.messa.injection.PerFragment;
import com.belichenko.a.messa.injection.module.FragmentModule;
import com.belichenko.a.messa.ui.fragments.ChooseLoginMethodFragment;

import dagger.Component;


@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(ChooseLoginMethodFragment chooseLoginMethodFragmentFragment);

}
