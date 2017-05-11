package com.sage.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sage.R;
import com.sage.bean.PhotosBean;
import com.sage.bean.PostDetailBean;
import com.sage.bean.TimelineResponse;
import com.sage.utils.Constants;
import com.sage.utils.Utils;
import com.sage.view.activity.ProfileActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by Anuj Sharma on 5/11/2017.
 */

public class TimelineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<TimelineResponse> mResponse;
    private AnimationDrawable frameAnimation;
    private int lastPosition = -1;

    public final static int COLOR_ANIMATION_DURATION = 1000;
    private int mDefaultBackgroundColor;

    public TimelineAdapter(Context ctx, List<TimelineResponse> response) {
        this.mContext = ctx;
        this.mResponse = response;
    }

    public void updateList(List<TimelineResponse> response) {
        this.mResponse = response;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView;
        RecyclerView.ViewHolder vh;
        rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_photo, parent, false);
        vh = new TimelineViewHolder(rowView);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final TimelineViewHolder vh = (TimelineViewHolder) holder;
        try {
           /* Picasso.with(mContext).load(mResponse.get(position).photoUrl).resize(500, 500).centerCrop().into(vh.mImage);

            Picasso.with(mContext)
                    .load(mResponse.get(position).photoUrl)
                    .resize(500, 500).centerCrop()
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                            *//* Save the bitmap or do something with it here *//*
                            //Set it in the ImageView
                            vh.mImage.setImageBitmap(bitmap);
                            *//*
                            Use Pallet For Getting Color
                             *//*
                            Palette palette = Palette.from(bitmap).generate();
                            if (palette != null) {
                                Palette.Swatch s = palette.getVibrantSwatch();
                                if (s == null) {
                                    s = palette.getDarkVibrantSwatch();
                                }
                                if (s == null) {
                                    s = palette.getLightVibrantSwatch();
                                }
                                if (s == null) {
                                    s = palette.getMutedSwatch();
                                }

                                if (s != null) {
                                    vh.mParentLayout.setBackgroundColor(s.getTitleTextColor());
//                                    vh.mInfoContainer.setBackgroundColor(s.getTitleTextColor());
//                                    vh.mTitle.setTextColor(s.getTitleTextColor());
//                                    vh.mPrice.setTextColor(s.getTitleTextColor());
                                }
                                Utils.getInstance().animateViewColor(vh.mParentLayout, mDefaultBackgroundColor, s.getRgb());
                            }
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return mResponse==null?0:mResponse.size();
    }

    /*
    View Holder For Trip History
     */
    private class TimelineViewHolder extends RecyclerView.ViewHolder {
        //        CardView mCardView;
        private LinearLayout mParentLayout;
        private ImageView mImage;
        ;

        private TimelineViewHolder(View itemView) {
            super(itemView);
            mParentLayout = (LinearLayout) itemView.findViewById(R.id.parent);
            mImage = (ImageView) itemView.findViewById(R.id.image);

            mParentLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
//                    manageShortView(v, mResponse.get(getAdapterPosition()));
                    return false;
                }
            });

            mParentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PostDetailBean detailObj = new PostDetailBean();
                    detailObj.commentCount = 115;
                    detailObj.isLiked = false;
                    detailObj.commentCount = 10;
                    detailObj.ownerName = "Anuj Sharma";
                    detailObj.ownerPicUrl = Constants.DEF_PROFILE_URL;
//                    detailObj.photoName = mResponse.get(getAdapterPosition()).photoName;
//                    detailObj.photoUrl = mResponse.get(getAdapterPosition()).photoUrl;

                    Intent intent = new Intent(mContext, ProfileActivity.class);
                    intent.putExtra("destination", "post_detail");
                    intent.putExtra("post_detail", detailObj);
                    if (Utils.getInstance().isEqualLollipop()) {
                        Activity activity = (Activity) mContext;
                        Pair<View, String> p1 = Pair.create((View) mImage, "detail_image");
                        ActivityOptions options =
                                ActivityOptions.makeSceneTransitionAnimation(activity, p1);
                        mContext.startActivity(intent, options.toBundle());
                    } else {
                        mContext.startActivity(intent);
                    }
                }
            });

        }
    }

}
