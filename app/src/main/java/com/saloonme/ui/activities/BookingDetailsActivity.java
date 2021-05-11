package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.saloonme.R;
import com.saloonme.interfaces.IBookingDetailsView;
import com.saloonme.interfaces.StringConstants;
import com.saloonme.model.response.BookingDetailsResponse;
import com.saloonme.model.response.BookingDetailsResponseData;
import com.saloonme.model.response.BookingServices;
import com.saloonme.model.response.UpcomingDetail;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.BookingDetailsPresenter;
import com.saloonme.ui.adapters.BookingDetailsAdapter;
import com.saloonme.ui.adapters.ProductsAdapter;

import butterknife.BindView;

public class BookingDetailsActivity extends BaseAppCompactActivity implements IBookingDetailsView {
    private UpcomingDetail upcomingDetail;
    private BookingDetailsPresenter bookingDetailsPresenter;
    private BookingDetailsAdapter bookingDetailsAdapter;

    @BindView(R.id.tv_userName)
    TextView tv_userName;
    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_salonName)
    TextView tv_salonName;
    @BindView(R.id.tv_salonaddreess)
    TextView tv_salonaddreess;
    @BindView(R.id.tv_salonNumber)
    TextView tv_salonNumber;
    @BindView(R.id.tv_booking_date_time)
    TextView tv_booking_date_time;
    @BindView(R.id.tv_booking_id)
    TextView tv_booking_id;
    @BindView(R.id.service_rv)
    RecyclerView service_rv;
    @BindView(R.id.tv_Total)
    TextView tv_Total;
    @BindView(R.id.tv_subTotal)
    TextView tv_subTotal;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_booking_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        upcomingDetail = (UpcomingDetail) getIntent().
                getSerializableExtra(StringConstants.EXTRA_DETAILS);
        if (upcomingDetail == null) {
            showToast("Something went wrong");
            finish();
        }
        bookingDetailsPresenter = new BookingDetailsPresenter(APIClient.getAPIService(),
                this);
        initRecyclerView();
        bookingDetailsPresenter.getBookingDetails(upcomingDetail.getBookingId());
    }

    private void initRecyclerView() {
        bookingDetailsAdapter = new BookingDetailsAdapter(getContext(), null);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        service_rv.setLayoutManager(saloonListManager);
        service_rv.setAdapter(bookingDetailsAdapter);
        service_rv.setNestedScrollingEnabled(false);
    }

    @Override
    public void onBookingDetailsSuccess(BookingDetailsResponse bookingDetailsResponse) {
        if (bookingDetailsResponse == null) {
            showToast("Failed to get the booking details");
            return;
        }
        if (bookingDetailsResponse.getStatus().toLowerCase().contains("fail")) {
            showToast(bookingDetailsResponse.getMessage());
            return;
        }
        if (bookingDetailsResponse.getData() == null) {
            showToast(bookingDetailsResponse.getMessage());
            return;
        }
        updateViewsData(bookingDetailsResponse.getData());
    }

    private void updateViewsData(BookingDetailsResponseData data) {
        if (data.getGetUserDetails() != null) {
            tv_userName.setText(data.getGetUserDetails().getFirstName() + " " +
                    data.getGetUserDetails().getLastName());
            tv_email.setText("Email : " + data.getGetUserDetails().getEmailAddress());
            tv_phone.setText("Phone : " + data.getGetUserDetails().getMobileNumber());
        }
        if (data.getBookingServicesList() != null && data.getBookingServicesList().size() > 0) {
            updateSaloonDetails(data.getBookingServicesList().get(0));
            bookingDetailsAdapter.setData(data.getBookingServicesList());
        }
    }

    private void updateSaloonDetails(BookingServices bookingServices) {
        tv_salonName.setText(bookingServices.getSalonName());
        //tv_salonaddreess.setText(bookingServices.get);
        // tv_salonNumber.setText(bookingServices.get);
        tv_booking_date_time.setText(bookingServices.getServiceDate() + " " +
                bookingServices.getServiceTime());
        tv_booking_id.setText("Booking Id : " + bookingServices.getBookingId());
        tv_subTotal.setText("SUBTOTAL \n Rs." + bookingServices.getTotalPrice());
        tv_Total.setText("TOTAL \n Rs." + bookingServices.getTotalPrice());
    }

    @Override
    public void onBookingDetailsFailed() {
        showToast("Failed to get the booking details");
    }
}