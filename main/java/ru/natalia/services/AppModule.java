package ru.natalia.services;

import java.io.IOException;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.Id;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Autobuild;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.annotations.ServiceId;
import org.apache.tapestry5.services.ApplicationStateContribution;
import org.apache.tapestry5.services.ApplicationStateCreator;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestFilter;
import org.apache.tapestry5.services.RequestHandler;
import org.apache.tapestry5.services.Response;
import org.slf4j.Logger;

import ru.natalia.pages.security.SecurityFilter;
import ru.natalia.services.model.User;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class AppModule
{
    public static void bind(ServiceBinder binder)
    {
        // binder.bind(MyServiceInterface.class, MyServiceImpl.class);

        // Make bind() calls on the binder object to define most IoC services.
        // Use service builder methods (example below) when the implementation
        // is provided inline, or requires more initialization than simply
        // invoking the constructor.
    }

    public static void contributeFactoryDefaults(
            MappedConfiguration<String, Object> configuration)
    {
        // The application version number is incorprated into URLs for some
        // assets. Web browsers will cache assets because of the far future expires
        // header. If existing assets are changed, the version number should also
        // change, to force the browser to download new versions. This overrides Tapesty's default
        // (a random hexadecimal number), but may be further overriden by DevelopmentModule or
        // QaModule.
        configuration.override(SymbolConstants.APPLICATION_VERSION, "1.0-SNAPSHOT");
    }

    public static void contributeApplicationDefaults(
            MappedConfiguration<String, Object> configuration)
    {
        // Contributions to ApplicationDefaults will override any contributions to
        // FactoryDefaults (with the same key). Here we're restricting the supported
        // locales to just "en" (English). As you add localised message catalogs and other assets,
        // you can extend this list of locales (it's a comma separated series of locale names;
        // the first locale name is the default when there's no reasonable match).
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "ru");
    }
 public void contributeApplicationStateManager(MappedConfiguration<Class<User>, ApplicationStateContribution> configuration)
 {
	 ApplicationStateCreator<User> creator = new ApplicationStateCreator<User>() {

		public User create() {
			System.out.println("Create new USER");
			return new User();
		}
	};
	
	configuration.add(User.class, new ApplicationStateContribution("session", creator));
 }
    
    public void contributeRequestHandler(OrderedConfiguration<RequestFilter> configuration, @Autobuild SecurityFilter filter)
    {
    	configuration.add("SecurityFilter", filter, "before:AjaxFilter");
    }

    /**
     * This is a contribution to the RequestHandler service configuration. This is how we extend
     * Tapestry using the timing filter. A common use for this kind of filter is transaction
     * management or security. The @Local annotation selects the desired service by type, but only
     * from the same module.  Without @Local, there would be an error due to the other service(s)
     * that implement RequestFilter (defined in other modules).
     */
//    public void contributeRequestHandler(OrderedConfiguration<RequestFilter> configuration,
//                                         @InjectService("TimingFilter")
//                                         RequestFilter filter)
//    {
//        // Each contribution to an ordered configuration has a name, When necessary, you may
//        // set constraints to precisely control the invocation order of the contributed filter
//        // within the pipeline.
//
//        configuration.add("TimingFilter", filter);
//    }
}
