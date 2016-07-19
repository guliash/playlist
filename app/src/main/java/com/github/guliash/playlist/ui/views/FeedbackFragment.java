package com.github.guliash.playlist.ui.views;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.github.guliash.playlist.R;

import butterknife.Bind;
import butterknife.ButterKnife;

//TODO There is a problem if toast shown. Fab will be overlapped
//TODO make presenter
public class FeedbackFragment extends BaseFragment {

    private static final String EXAMPLE_MAIL = "guliash@example.com";
    private static final String SUBJECT = "Playlist feedback";

    @Bind(R.id.feedback)
    EditText mFeedbackEdit;

    @Bind(R.id.fab)
    FloatingActionButton mSendFab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        ButterKnife.bind(this, view);

        mSendFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail(mFeedbackEdit.getText().toString(), EXAMPLE_MAIL, SUBJECT);
            }
        });
        return view;
    }

    private void sendMail(@NonNull String text, @NonNull String email, @NonNull String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        try {
            startActivity(Intent.createChooser(intent, "Send feedback..."));
        } catch (ActivityNotFoundException e) {
            Snackbar.make(mSendFab, R.string.no_mail_app_error, Snackbar.LENGTH_SHORT);
        }
    }
}
