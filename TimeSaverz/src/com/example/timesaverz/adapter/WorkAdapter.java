package com.example.timesaverz.adapter;

import com.example.timesaverz.model.*;
import com.example.timesaverz.*;

import java.util.ArrayList;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timesaverz.R;



public class WorkAdapter extends ArrayAdapter<WorkModel> {

	private final Activity context;
	private ArrayList<WorkModel> names;
	 WorkModel tempValues = null;
	 int i = 0;
	
	public WorkAdapter(Activity context, ArrayList<WorkModel> names) {
		super(context, R.layout.listview_row,names);
		this.context=context;
		this.names=names;
		
	}
	
	static class ViewHolder{
		 public TextView name, email ,image;
		 public ImageView imgViewLogo,img2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		final ViewHolder holder;
		View rowView=convertView;
		if(rowView==null){
			 LayoutInflater inflater = context.getLayoutInflater();
			rowView = inflater.inflate(R.layout.listview_row, null, true);
			            	holder = new ViewHolder();
				            holder.name = (TextView) rowView.findViewById(R.id.tv1);
				            holder.email = (TextView) rowView.findViewById(R.id.tv2);
				            holder.img2 = (ImageView) rowView.findViewById(R.id.img2);
				            holder.imgViewLogo = (ImageView) rowView.findViewById(R.id.imgViewLogo);
				            rowView.setTag(holder);
		}else{
			holder=(ViewHolder)rowView.getTag();
		}
		
		
		
		
		WorkModel blipVar=names.get(position);
		if(blipVar!=null)
		{
			holder.name.setText(blipVar.getName());
			holder.email.setText(blipVar.getEmail());
			        
		}
		// rowView.setOnClickListener((OnClickListener) new OnItemClickListener( position ));
		return rowView;
		
	}
	
	/*
	 public void onClick(View v) {
          Log.v("CustomAdapter", "=====Row button clicked=====");
  }
	  
	  //Called when Item click in ListView 
      private class OnItemClickListener  implements OnClickListener{           
          private int mPosition;
           
          OnItemClickListener(int position){
               mPosition = position;
               
          }
           
          @Override
          public void onClick(View arg0) {

     
            MainActivity sct = (MainActivity)context;

           //  Call  onItemClick Method inside MainActivity Class ( See Below )

              sct.onItemClick(mPosition);
          }     
          
          
      } 
      */
     
}
