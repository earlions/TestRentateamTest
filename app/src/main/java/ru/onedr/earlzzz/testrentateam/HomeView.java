package ru.onedr.earlzzz.testrentateam;

import java.util.List;

import ru.onedr.earlzzz.testrentateam.recyclerview.Post;

public interface HomeView {
    interface View {
        void showData(List<Post> posts);
        void showComplete();

        void showError(String textError);

        void showProgress();

        void hideProgress();
    }

    interface Presenter {
        void onLoadBD();
    }

    interface Repository {
        void loadMessage();
    }
}
