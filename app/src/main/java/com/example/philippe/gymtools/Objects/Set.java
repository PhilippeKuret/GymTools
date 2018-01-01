package com.example.philippe.gymtools.Objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "exercise", foreignKeys = @ForeignKey(entity =
															Exercise.class,
															onDelete = CASCADE,
															parentColumns = "id",
															childColumns = "exercise_id"))
public class Set implements Parcelable
{
	public Set()
	{
	}

	public Set(int id, String name, double weight,int exerciseId)
	{
		Id = id;
		Name = name;
		Weight = weight;
		this.exerciseId = exerciseId;
	}

	@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
	private int Id;

	@ColumnInfo(name = "name")
	private String Name;

	@ColumnInfo(name = "weight")
	private double Weight;

	@ColumnInfo(name = "exercise_id")
	private int exerciseId;

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

	public double getWeight()
	{
		return Weight;
	}

	public void setWeight(double weight)
	{
		Weight = weight;
	}

	public int getPlanId()
	{
		return exerciseId;
	}

	public void setPlanId(int exerciseId)
	{
		this.exerciseId = exerciseId;
	}

	protected Set(Parcel in)
	{
		Id = in.readInt();
		Name = in.readString();
		Weight = in.readDouble();
		exerciseId = in.readInt();
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
		dest.writeDouble(Weight);
		dest.writeInt(exerciseId);
	}

	public static final Creator<Set> CREATOR = new Creator<Set>()
	{
		@Override
		public Set createFromParcel(Parcel in)
		{
			return new Set(in);
		}

		@Override
		public Set[] newArray(int size)
		{
			return new Set[size];
		}
	};
}
