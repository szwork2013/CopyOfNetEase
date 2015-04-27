package me.cyning.news.ui.adapters;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jumige.android.common.adapter.ArrayListAdapter;
import com.jumige.android.common.utils.DisplayUtil;
import com.jumige.android.common.utils.StringUtils;
import com.jumige.android.common.utils.ViewUtil;
import com.jumige.android.ui.template.utils.CommonViewHolder;

import cyning.me.libnerss.rss.Channel.ArticleItem;
import me.cyning.news.R;

/**
 * Author: cyning
 * Date  : 2015.04.27
 * Time  : 下午12:27
 * Desc  : 新闻列表
 */
public class NewsAdapters extends ArrayListAdapter<ArticleItem> {
    public static final float RATIO_BANNER_PIC = 400 / 640f;

    public static final int TYPE_HEADER = 0;

    public static final int TYPE_COVER_TEXT = 1;

    public static final int TYPE_TEXT = 2;


    private Fragment mFragment =null;

    public NewsAdapters(Fragment _fragment) {
        super(_fragment.getActivity(),true);
        this.mFragment = _fragment;

    }

    @Override
    public int getItemViewType(int position) {
        ArticleItem mArticleItem = getItem(position);

        if (!StringUtils.isEmpty(mArticleItem.getImgsrc()) && position == 0){
            return  TYPE_HEADER;
        }else if (!StringUtils.isEmpty(mArticleItem.getImgsrc())){
            return  TYPE_COVER_TEXT;
        }else{
            return  TYPE_TEXT;
        }
    }

    @Override
    public int getViewTypeCount() {


        return 3 + super.getViewTypeCount();
    }

    @Override
    public ArticleItem getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int type = getItemViewType(position);

        if (convertView == null){

            if (type == TYPE_COVER_TEXT){
                convertView = View.inflate(mContext, R.layout.item_article_cover_text,null);
            }else if (TYPE_HEADER == type){
                convertView = View.inflate(mContext,R.layout.item_article_header_cover,null);
                ImageView ivCover = ViewUtil.find(convertView,R.id.ivCover);
                ViewGroup.LayoutParams params = ivCover.getLayoutParams();
                params.height = (int) (DisplayUtil.getScreenWidthInPx(mContext) * RATIO_BANNER_PIC);


            }else if (TYPE_TEXT == type){
                convertView = View.inflate(mContext,R.layout.item_article_only_text,null);


            }

        }


        ImageView ivCover      = CommonViewHolder.get(convertView,R.id.ivCover);
        TextView  tvTitle      = CommonViewHolder.get(convertView,R.id.tvTitle);
        TextView  tvSubTitle   = CommonViewHolder.get(convertView,R.id.tvSubTitle);



        ArticleItem mItem = getItem(position);


        if (ivCover != null){

            Glide.with(mFragment)
                    .load(mItem.getImgsrc())
                    .centerCrop()
                    .placeholder(R.drawable.icon_title)
                    .crossFade()
                    .into(ivCover);

        }

        if (tvTitle != null){
            tvTitle.setText(getItem(position).getTitle());
        }
        if (tvSubTitle != null){
            tvSubTitle.setText(getItem(position).getDigest());

        }


        return convertView;
    }



    public class  ViewHolderType1 {



    }



}
