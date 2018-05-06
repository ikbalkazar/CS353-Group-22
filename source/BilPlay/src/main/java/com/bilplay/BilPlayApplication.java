package com.bilplay;

import com.bilplay.auth.AuthFeature;
import com.bilplay.db.MainDao;
import com.bilplay.resources.MainResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.jdbi.v3.core.Jdbi;

public class BilPlayApplication extends Application<BilPlayConfiguration> {

    public static void main(final String[] args) throws Exception {
        new BilPlayApplication().run(args);
    }

    @Override
    public String getName() {
        return "BilPlay";
    }

    @Override
    public void initialize(final Bootstrap<BilPlayConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<BilPlayConfiguration>());
    }

    @Override
    public void run(final BilPlayConfiguration configuration,
                    final Environment environment) {
        final JdbiFactory dbFactory = new JdbiFactory();
        final Jdbi db = dbFactory.build(environment, configuration.getDataSourceFactory(), "mysql");
        final MainDao dao = db.onDemand(MainDao.class);

        environment.jersey().register(new AuthFeature(dao));

        final MainResource main = new MainResource(dao);
        environment.jersey().register(main);
    }

}
