package com.kent.hottnights.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.kent.hottnights.R;
import com.kent.hottnights.objects.ShareListObjs;

public class ShareAdapter extends BaseAdapter {

	Context context;

	protected List<ShareListObjs> listShare;

	public ShareAdapter(Context context, List<ShareListObjs> listShare) {
		super();
		this.context = context;
		this.listShare = listShare;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listShare.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listShare.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return listShare.get(position).getShareLid();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();

			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.share_main_layout, parent,
					false);

			holder.shareImg = (ImageView) convertView
					.findViewById(R.id.ivShareListImg);
			holder.shareName = (TextView) convertView
					.findViewById(R.id.tvShareListName);
			holder.shareDescr = (TextView) convertView
					.findViewById(R.id.tvShareListDescr);
			holder.checker = (ImageView) convertView.findViewById(R.id.ivClickCheck);
		//	holder.shareCheck = (CheckBox) convertView
			//		.findViewById(R.id.cbShareList);

			ShareListObjs which = listShare.get(position);
			boolean isitreally;
			isitreally = which.isShareIcheck();
			holder.shareImg.setImageResource(which.getShareLimg());
			holder.shareName.setText(which.getShareLname());
			holder.shareDescr.setText(which.getShareDescr());
			holder.checker.setImageResource(R.drawable.icon_profile);
			
			/*if ( listShare.contains(position))
			{
				convertView.setBackgroundColor(Color.parseColor("#FFFFF"));
			}else
			{
				convertView.setBackgroundColor(Color.parseColor("#FF0000"));
			}
			*/
			//holder.shareCheck.setChecked(isitreally);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	private class ViewHolder {
		ImageView shareImg;
		TextView shareName;
		TextView shareDescr;
		//CheckBox shareCheck;
		ImageView checker;
	}

}
