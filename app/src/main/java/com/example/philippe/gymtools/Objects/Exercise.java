package com.example.philippe.gymtools.Objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "exercise")
public class Exercise implements Parcelable
{
	public Exercise()
	{
	}

	public Exercise(int id, String name)
	{
		Id = id;
		Name = name;
	}

	@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
	private int Id;

	@ColumnInfo(name = "name")
	private String Name;

	protected Exercise(Parcel in)
	{
		Id = in.readInt();
		Name = in.readString();
	}

	public static final Creator<Exercise> CREATOR = new Creator<Exercise>()
	{
		@Override
		public Exercise createFromParcel(Parcel in)
		{
			return new Exercise(in);
		}

		@Override
		public Exercise[] newArray(int size)
		{
			return new Exercise[size];
		}
	};

	public int getId()
	{
		return Id;
	}

	public void setId(int id)
	{
		Id = id;
	}

	public String getName()
	{
		return Name;
	}

	public void setName(String name)
	{
		this.Name = name;
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeInt(Id);
		dest.writeString(Name);
	}
}
