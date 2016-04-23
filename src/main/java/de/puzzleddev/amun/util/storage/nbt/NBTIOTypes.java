package de.puzzleddev.amun.util.storage.nbt;

import de.puzzleddev.amun.util.storage.IDataIO;
import de.puzzleddev.amun.util.storage.IOTypesImpl;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.common.util.Constants.NBT;

/**
 * IO types for NBT data.<br>
 * 
 * TODO: Comment it (I definitely won't)
 * 
 * @author tim4242
 */
public class NBTIOTypes extends IOTypesImpl<NBTBase>
{
	private static NBTIOTypes m_instance;

	public static NBTIOTypes instance()
	{
		if(m_instance == null)
		{
			m_instance = new NBTIOTypes();
		}

		return m_instance;
	}

	private NBTIOTypes()
	{
		set(Boolean.class, new IDataIO<Boolean, NBTBase>()
		{
			@Override
			public NBTBase write(Boolean data)
			{
				return new NBTTagByte((data ? (byte) 0 : (byte) 1));
			}

			@Override
			public Boolean read(NBTBase data)
			{
				if(data.getId() != NBT.TAG_BYTE)
					return null;

				return (((NBTTagByte) data).getByte() == 0 ? false : true);
			}
		});
		set(boolean.class, get(Boolean.class));

		set(Short.class, new IDataIO<Short, NBTBase>()
		{
			@Override
			public NBTBase write(Short data)
			{
				return new NBTTagShort(data);
			}

			@Override
			public Short read(NBTBase data)
			{
				if(data.getId() != NBT.TAG_SHORT)
					return null;

				return ((NBTTagShort) data).getShort();
			}
		});
		set(short.class, get(Short.class));

		set(Integer.class, new IDataIO<Integer, NBTBase>()
		{
			@Override
			public NBTBase write(Integer data)
			{
				return new NBTTagInt(data);
			}

			@Override
			public Integer read(NBTBase data)
			{
				if(data.getId() != NBT.TAG_INT)
					return null;

				return ((NBTTagInt) data).getInt();
			}
		});
		set(int.class, get(Integer.class));

		set(Long.class, new IDataIO<Long, NBTBase>()
		{
			@Override
			public NBTBase write(Long data)
			{
				return new NBTTagLong(data);
			}

			@Override
			public Long read(NBTBase data)
			{
				if(data.getId() != NBT.TAG_LONG)
					return null;

				return ((NBTTagLong) data).getLong();
			}
		});
		set(long.class, get(Long.class));

		set(Float.class, new IDataIO<Float, NBTBase>()
		{
			@Override
			public NBTBase write(Float data)
			{
				return new NBTTagFloat(data);
			}

			@Override
			public Float read(NBTBase data)
			{
				if(data.getId() != NBT.TAG_FLOAT)
					return null;

				return ((NBTTagFloat) data).getFloat();
			}
		});
		set(float.class, get(Float.class));

		set(Double.class, new IDataIO<Double, NBTBase>()
		{
			@Override
			public NBTBase write(Double data)
			{
				return new NBTTagDouble(data);
			}

			@Override
			public Double read(NBTBase data)
			{
				if(data.getId() != NBT.TAG_DOUBLE)
					return null;

				return ((NBTTagDouble) data).getDouble();
			}
		});
		set(double.class, get(Double.class));

		set(String.class, new IDataIO<String, NBTBase>()
		{
			@Override
			public NBTBase write(String data)
			{
				return new NBTTagString(data);
			}

			@Override
			public String read(NBTBase data)
			{
				if(data.getId() != NBT.TAG_STRING)
					return null;

				return ((NBTTagString) data).getString();
			}
		});

		set(ItemStack.class, new IDataIO<ItemStack, NBTBase>()
		{
			@Override
			public NBTBase write(ItemStack data)
			{
				return data.writeToNBT(new NBTTagCompound());
			}

			@Override
			public ItemStack read(NBTBase data)
			{
				if(data.getId() == NBT.TAG_COMPOUND)
					return null;

				ItemStack res = new ItemStack(Items.apple);

				res.readFromNBT((NBTTagCompound) data);

				return res;
			}
		});

		set(NBTBase.class, new IDataIO<NBTBase, NBTBase>()
		{
			@Override
			public NBTBase write(NBTBase data)
			{
				return data;
			}

			@Override
			public NBTBase read(NBTBase data)
			{
				return data;
			}
		});

		set(Vec3i.class, new IDataIO<Vec3i, NBTBase>()
		{
			@Override
			public NBTBase write(Vec3i data)
			{
				NBTTagCompound comp = new NBTTagCompound();

				comp.setInteger("x", data.getX());
				comp.setInteger("y", data.getY());
				comp.setInteger("z", data.getZ());

				return comp;
			}

			@Override
			public Vec3i read(NBTBase data)
			{
				if(data.getId() != NBT.TAG_COMPOUND)
					return null;

				return new Vec3i(((NBTTagCompound) data).getInteger("x"), ((NBTTagCompound) data).getInteger("y"), ((NBTTagCompound) data).getInteger("z"));
			}
		});

		set(BlockPos.class, new IDataIO<BlockPos, NBTBase>()
		{
			@Override
			public NBTBase write(BlockPos data)
			{
				NBTTagCompound comp = new NBTTagCompound();

				comp.setInteger("x", data.getX());
				comp.setInteger("y", data.getY());
				comp.setInteger("z", data.getZ());

				return comp;
			}

			@Override
			public BlockPos read(NBTBase data)
			{
				if(data.getId() != NBT.TAG_COMPOUND)
					return null;

				return new BlockPos(((NBTTagCompound) data).getInteger("x"), ((NBTTagCompound) data).getInteger("y"), ((NBTTagCompound) data).getInteger("z"));
			}
		});
	}
}
