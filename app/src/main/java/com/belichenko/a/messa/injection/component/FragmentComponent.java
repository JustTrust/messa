package com.belichenko.a.messa.injection.component;


import com.belichenko.a.messa.injection.PerFragment;
import com.belichenko.a.messa.injection.module.FragmentModule;

import dagger.Component;


@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {


}
