package ru.onedr.earlzzz.testrentateam.fragment;

import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.onedr.earlzzz.testrentateam.CardUserActivity;
import ru.onedr.earlzzz.testrentateam.HomeView;
import ru.onedr.earlzzz.testrentateam.PresenterLoadData;
import ru.onedr.earlzzz.testrentateam.R;
import ru.onedr.earlzzz.testrentateam.recyclerview.Post;


public class HomeFragment extends Fragment implements HomeView.View {
    private RecyclerView mRecyclerView;
    private TestResAdapter mAdapter;
    private List<Post> posts = new ArrayList<>();
    private ProgressBar progressBar;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_list, container,
                false);
        PresenterLoadData presenterLoadData = new PresenterLoadData(this, getContext(), getActivity());
        progressBar=view.findViewById(R.id.progressBar);
        mRecyclerView = view.findViewById(R.id.settings_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setNestedScrollingEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        presenterLoadData.onLoadBD();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showText(List<Post> posts) {
        mAdapter = new TestResAdapter(posts);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void offLoad() {
        progressBar.setVisibility(View.GONE);
    }


    private class TestHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;
        private RelativeLayout mRLView;

        TestHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.textView_home);
            mImageView = itemView.findViewById(R.id.image_crime);
            mRLView = itemView.findViewById(R.id.rl_sitt);
        }

        void bindCrime(Post mPost) {
            mTextView.append(mPost.getFirsName()+" "+mPost.getLastName());
            Picasso.get().load(mPost.getAvatarSrc()).fit().centerInside().into(mImageView);
        }


    }

    public class TestResAdapter extends RecyclerView.Adapter<TestHolder> {
        private List<Post> mPosts;

        TestResAdapter(List<Post> logoSettingData) {
            mPosts = logoSettingData;
        }

        @NonNull
        @Override
        public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_home, parent, false);
            return new TestHolder(view);
        }

        @Override
        public void onBindViewHolder(TestHolder holder, int position) {
            Post post = mPosts.get(position);
            holder.bindCrime(post);
            (holder).mRLView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CardUserActivity.class);
                intent.putExtra("firstName", mPosts.get(position).getFirsName());
                intent.putExtra("lastName", mPosts.get(position).getLastName());
                intent.putExtra("email", mPosts.get(position).getUserEmail());
                intent.putExtra("avatar", mPosts.get(position).getAvatarSrc());
                v.getContext().startActivity(intent);
            });
        }


        @Override
        public int getItemCount() {
            return mPosts.size();
        }
    }


}
