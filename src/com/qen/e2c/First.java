package com.qen.e2c;

/*
 * 作者：QenKang,采用了jxl.jar获取Excel中的数据
 */
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.excel2.R;

import jxl.Sheet;
import jxl.Workbook;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class First extends ActionBarActivity {
	private List<Info> list = new ArrayList<Info>();
	ListView ls;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		try {
			setContentView(R.layout.showname);
			Workbook book = Workbook.getWorkbook(new File(Environment
					.getExternalStorageDirectory().getPath() + "/mytest.xls"));
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			int Rows = sheet.getRows();
			int Cols = sheet.getColumns();
			Toast.makeText(this, sheet.getName(), 0).show();
			ls = (ListView) findViewById(R.id.myll);
			for (int i = 0; i < Rows; ++i) {// n行
				// 2列
				// getCell(Col,Row)获得单元格的值
				Info info = new Info();
				info.setname((sheet.getCell(0, i)).getContents());
				info.setNumber((sheet.getCell(1, i)).getContents());
				list.add(info);
			}
			MyAdapter adapter = new MyAdapter(this, list);
			ls.setAdapter(adapter);
			book.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void showInfo(View v, String a, final String b) {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(this)
				.setTitle(a)
				.setMessage("号码:" + b)
				.setPositiveButton("打电话",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Intent intent = new Intent(Intent.ACTION_CALL,
										Uri.parse("tel:" + b));
								startActivity(intent);
							}
						})
				.setNegativeButton("发短信",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Intent it = new Intent(Intent.ACTION_SENDTO,
										Uri.parse("smsto:" + b));
								startActivity(it);
							}
						}).show();
	}

	public class MyAdapter extends BaseAdapter {
		private List<Info> list;
		private Context ctx;
		LayoutInflater mInflater;

		public MyAdapter(Context context, List<Info> list) {
			// TODO Auto-generated constructor stub
			ctx = context;
			this.list = list;
			mInflater = LayoutInflater.from(context);// 生成动态加载器
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View v, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder viewHolder;
			Info info = list.get(position);
			if (v == null) {
				v = mInflater.inflate(R.layout.item, parent, false);
				viewHolder = new ViewHolder();
				viewHolder.name = (TextView) v.findViewById(R.id.name);
				viewHolder.number = (TextView) v.findViewById(R.id.number);
				viewHolder.bt = (Button) v.findViewById(R.id.call);
				v.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) v.getTag();
			}
			viewHolder.name.setText(info.getname());
			viewHolder.number.setText(info.getNumber());
			final String a = viewHolder.name.getText().toString();
			final String b = viewHolder.number.getText().toString();
			if (position == 0) {
				viewHolder.bt.setVisibility(View.INVISIBLE);
			} else {
				viewHolder.bt.setVisibility(View.VISIBLE);
			}
			viewHolder.bt.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					showInfo(v, a, b);
				}
			});
			return v;
		}

		class ViewHolder {
			TextView name;
			TextView number;
			Button bt;
		}
	}
}
