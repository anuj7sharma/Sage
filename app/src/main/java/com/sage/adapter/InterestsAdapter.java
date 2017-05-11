package com.sage.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sage.R;
import com.sage.bean.GetInterestsResponse;
import com.sage.utils.Utils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by Anuj Sharma on 5/11/2017.
 */

public class InterestsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private  GetInterestsResponse mResponse;
    private AnimationDrawable frameAnimation;
    private int lastPosition = -1;

    public final static int COLOR_ANIMATION_DURATION = 1000;
    private int mDefaultBackgroundColor;

    public InterestsAdapter(Context ctx, GetInterestsResponse response) {
        this.mContext = ctx;
        this.mResponse = response;
    }
    public void updateInterest(GetInterestsResponse response){
        this.mResponse = response;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView;
        RecyclerView.ViewHolder vh;
        rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_interest, parent, false);
        vh = new InterestViewHolder(rowView);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final InterestViewHolder vh = (InterestViewHolder) holder;
        try {
            vh.mInterestName.setText(mResponse.getData().get(position).getName());
            Picasso.with(mContext).load(mResponse.getData().get(position).getImage()).resize(500,500).centerCrop().into(vh.mImage);

            Picasso.with(mContext)
                    .load(mResponse.getData().get(position).getImage())
                    .resize(500, 500).centerCrop()
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                            /* Save the bitmap or do something with it here */
                            //Set it in the ImageView
                            vh.mImage.setImageBitmap(bitmap);
                            /*
                            Use Pallet For Getting Color
                             */
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
                                    vh.mParentLayout.setBackgroundColor(s.getBodyTextColor());
                                    vh.mInterestName.setTextColor(s.getTitleTextColor());
                                    Utils.getInstance().animateViewColor(vh.mParentLayout, mDefaultBackgroundColor, s.getRgb());
                                }

                            }
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mResponse==null?0:mResponse.getData().size();
    }

    /*
    View Holder For Trip History
     */
    private class InterestViewHolder extends RecyclerView.ViewHolder {
        //        CardView mCardView;
        private LinearLayout mParentLayout;
        private ImageView mImage;
        private TextView mInterestName;

        private InterestViewHolder(View itemView) {
            super(itemView);
            mParentLayout = (LinearLayout) itemView.findViewById(R.id.parent);
            mImage = (ImageView) itemView.findViewById(R.id.image);
            mInterestName = (TextView) itemView.findViewById(R.id.tv_interest_name);
            mParentLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });


        }
    }
}
