package net.greenbeansit.jobtracker.client.components.kapa.data;

import net.greenbeansit.jobtracker.shared.Customer;
import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;

/**
 * Created by s1ckn3zz on 10.06.16.
 */
public class CustomerOption extends Option {

    private Customer customer;

    public CustomerOption(Customer customer) {
        this.customer = customer;
        this.setText(this.customer.getName());
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
