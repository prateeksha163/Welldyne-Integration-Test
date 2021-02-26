package pageobjects.clientconfiguration.overview;

import pageobjects._common.containers.FlatDataContainer;

public class About extends FlatDataContainer {

    public About(String name) {
        super(name);
    }

    public EditClientModal getEditClientModal(){
        return  new EditClientModal();
    }
}
